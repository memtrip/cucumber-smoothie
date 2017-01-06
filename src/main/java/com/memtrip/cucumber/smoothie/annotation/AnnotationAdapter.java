package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.Set;

public class AnnotationAdapter {

    public List<FeatureModel> getFeatureAnnotations(Set<? extends Element> elements) {

        ValueAdapter valueAdapter = new ValueAdapter();

        TypeAdapter typeAdapter = new TypeAdapter();

        BehaviourAdapter behaviourAdapter = new BehaviourAdapter(new ModelAdapter<BehaviourModel>(valueAdapter), typeAdapter);

        ScenarioAdapter scenarioAdapter =
                new ScenarioAdapter(new ModelAdapter<ScenarioModel>(valueAdapter), behaviourAdapter, typeAdapter);

        BackgroundAdapter backgroundAdapter =
                new BackgroundAdapter(new ModelAdapter<BackgroundModel>(valueAdapter), behaviourAdapter, typeAdapter);

        FeatureAdapter featureAdapter =
                new FeatureAdapter(new ModelAdapter<FeatureModel>(valueAdapter), scenarioAdapter, backgroundAdapter, typeAdapter);

        return featureAdapter.features(elements);
    }
}