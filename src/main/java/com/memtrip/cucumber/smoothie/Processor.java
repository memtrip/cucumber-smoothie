package com.memtrip.cucumber.smoothie;

import com.google.auto.service.AutoService;
import com.memtrip.cucumber.smoothie.annotation.AnnotationAdapter;
import com.memtrip.cucumber.smoothie.gherkin.*;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import com.memtrip.cucumber.smoothie.spec.*;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.pickles.Compiler;

import javax.annotation.processing.AbstractProcessor;
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
    private FreeMarker freeMarker;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        freeMarker = getFreeMarker();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
        Set<? extends Element> elements = env.getElementsAnnotatedWith(Feature.class);

        GherkinAdapter gherkinAdapter =
                new GherkinAdapter(
                        new GherkinParser(new Compiler(), new Parser<>(new AstBuilder())),
                        new ArgumentAdapter(new ArgumentTypeMatcher()),
                        new FileUtil()
                );

        List<FeatureModel> features = new AnnotationAdapter().getFeatureAnnotations(elements);


        for (FeatureModel featureModel : features) {
            FeatureGherkin featureGherkin = gherkinAdapter.create(featureModel);
            System.out.print("");
        }

        return true;
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

    private FreeMarker getFreeMarker() {
        try {
            return new FreeMarker();
        } catch (IOException e) {
            return null;
        }
    }
}