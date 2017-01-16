/**
 * Copyright 2013-present memtrip LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.memtrip.cucumber.smoothie;

import com.google.auto.service.AutoService;
import com.memtrip.cucumber.smoothie.annotation.AnnotationAdapter;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.freemarker.Source;
import com.memtrip.cucumber.smoothie.gherkin.GherkinAdapter;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import com.memtrip.cucumber.smoothie.spec.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AutoService(javax.annotation.processing.Processor.class)
public class Processor extends AbstractProcessor {

    private Source source;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);

        Log.createInstance(env.getMessager());

        source = createMapper(env.getFiler());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
        Set<? extends Element> elements = env.getElementsAnnotatedWith(Feature.class);

        if (source != null && elements != null && elements.size() > 0) {

            Log.note("cucumber.smoothie\\processing...");

            generateSources(elements);

            Log.note("cucumber.smoothie\\eof");

            return true;
        }

        return false;
    }

    private void generateSources(Set<? extends Element> elements) {
        List<FeatureModel> featureModels = new AnnotationAdapter().getFeatureAnnotations(elements);
        List<FeatureGherkin> featureGherkins = new GherkinAdapter(processingEnv.getFiler()).getFeatureGherkin(featureModels);

        String sourceCode = source.generate(featureGherkins);
        String formattedSourceCode = source.format(sourceCode);
        source.save("com.memtrip.cucumber.smoothie", "CucumberRunner", formattedSourceCode);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(Feature.class.getCanonicalName());
        set.add(Scenario.class.getCanonicalName());
        set.add(Given.class.getCanonicalName());
        set.add(When.class.getCanonicalName());
        set.add(Then.class.getCanonicalName());
        set.add(And.class.getCanonicalName());
        set.add(But.class.getCanonicalName());
        return set;
    }

    private Source createMapper(Filer filer) {
        try {
            return new Source(filer);
        } catch (IOException e) {
            return null;
        }
    }
}