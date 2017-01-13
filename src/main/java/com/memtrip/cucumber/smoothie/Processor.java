package com.memtrip.cucumber.smoothie;

import com.google.auto.service.AutoService;
import com.memtrip.cucumber.smoothie.annotation.AnnotationAdapter;
import com.memtrip.cucumber.smoothie.freemarker.Source;
import com.memtrip.cucumber.smoothie.gherkin.*;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import com.memtrip.cucumber.smoothie.spec.*;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;

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
        source = createMapper(env.getFiler());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
        Set<? extends Element> elements = env.getElementsAnnotatedWith(Feature.class);

        if (source != null && elements.size() > 0) {

            List<FeatureModel> featureModels = new AnnotationAdapter().getFeatureAnnotations(elements);

            List<FeatureGherkin> featureGherkins = new GherkinAdapter().getFeatureGherkin(featureModels);

            String sourceCode = source.generate(featureGherkins);
            String formattedSourceCode = source.format(sourceCode);
            source.save("com.memtrip.cucumber.smoothie","CucumberRunner", formattedSourceCode);

            return true;
        }

        return false;
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