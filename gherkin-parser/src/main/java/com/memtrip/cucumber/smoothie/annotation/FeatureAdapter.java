package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class FeatureAdapter {
    private ValueAdapter valueAdapter;
    private ScenarioAdapter scenarioAdapter;
    private BackgroundAdapter backgroundAdapter;
    private TypeAdapter typeAdapter;

    FeatureAdapter(ValueAdapter valueAdapter, ScenarioAdapter scenarioAdapter, BackgroundAdapter backgroundAdapter, TypeAdapter typeAdapter) {
        this.valueAdapter = valueAdapter;
        this.scenarioAdapter = scenarioAdapter;
        this.backgroundAdapter = backgroundAdapter;
        this.typeAdapter = typeAdapter;
    }

    List<FeatureModel> features(Set<? extends Element> elements) {

        List<FeatureModel> featureModels = new ArrayList<>();

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                FeatureModel featureModel = new FeatureModel();
                featureModel.setFeatureFilePath(valueAdapter.value(element, "featureFilePath"));
                featureModel.setProjectRootFolderName(valueAdapter.value(element, "projectRootFolderName"));
                featureModel.setClassName(typeAdapter.getName(element));
                featureModel.setScenarios(scenarioAdapter.scenarios(element.getEnclosedElements()));
                featureModel.setBackground(backgroundAdapter.background(element.getEnclosedElements()));

                featureModels.add(featureModel);
            }
        }

        return featureModels;
    }
}
