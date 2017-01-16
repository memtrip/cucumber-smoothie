package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.gherkin.model.BackgroundGherkin;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import gherkin.pickles.Pickle;

import java.util.List;

class FeatureGherkinAdapter {
    private ScenarioPickleAdapter scenarioPickleAdapter;

    FeatureGherkinAdapter(ScenarioPickleAdapter scenarioPickleAdapter) {
        this.scenarioPickleAdapter = scenarioPickleAdapter;
    }

    FeatureGherkin getFeatureGherkin(FeatureModel featureModel, List<Pickle> pickles) {
        FeatureGherkin featureGherkin = new FeatureGherkin();
        featureGherkin.setClassName(featureModel.getClassName());
        featureGherkin.setScenarioPickles(scenarioPickleAdapter.getScenarioPickles(
                featureModel.getScenarios(),
                featureModel.getBackground(),
                pickles
        ));

        if (featureModel.getBackground() != null) {
            BackgroundGherkin backgroundGherkin = new BackgroundGherkin();
            backgroundGherkin.setClassName(featureModel.getBackground().getClassName());
            backgroundGherkin.setPackageName(featureModel.getBackground().getPackageName());
            featureGherkin.setBackgroundGherkin(backgroundGherkin);
        }

        return featureGherkin;
    }
}