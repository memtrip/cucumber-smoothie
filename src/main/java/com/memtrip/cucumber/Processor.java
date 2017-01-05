package com.memtrip.cucumber;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.auto.service.AutoService;
import com.memtrip.cucumber.adapter.FeatureAdapter;
import com.memtrip.cucumber.adapter.ModelAdapter;
import com.memtrip.cucumber.annotations.*;
import com.memtrip.cucumber.adapter.ValueAdapter;
import com.memtrip.cucumber.model.FeatureModel;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;

import static com.memtrip.cucumber.Constants.VALUE;

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

        ValueAdapter valueAdapter = new ValueAdapter();
        ModelAdapter<FeatureModel> modelAdapter = new ModelAdapter<>(valueAdapter);


        FeatureAdapter featureAdapter = new FeatureAdapter(modelAdapter);
        List<FeatureModel> features = featureAdapter.features(elements);

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