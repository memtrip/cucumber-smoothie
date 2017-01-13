package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;

import java.util.ArrayList;
import java.util.List;

public class GherkinAdapter {

    private GherkinParser gherkinParser;

    public GherkinAdapter() {
        gherkinParser = new GherkinParser(new FileUtil(), new Compiler(), new Parser<>(new AstBuilder()));
    }

    public List<FeatureGherkin> getFeatureGherkin(List<FeatureModel> featureModels) {

        List<FeatureGherkin> featureGherkins = new ArrayList<>();

        ArgumentAdapter argumentAdapter = new ArgumentAdapter(new ArgumentTypeMatcher());
        BehaviourPickleAdapter behaviourPickleAdapter = new BehaviourPickleAdapter(argumentAdapter);
        ScenarioPickleAdapter scenarioPickleAdapter = new ScenarioPickleAdapter(behaviourPickleAdapter);
        FeatureGherkinAdapter featureGherkinAdapter = new FeatureGherkinAdapter(scenarioPickleAdapter);

        for (FeatureModel featureModel : featureModels) {
            List<Pickle> pickles = gherkinParser.getPickles(featureModel.getValue());
            featureGherkins.add(featureGherkinAdapter.getFeatureGherkin(featureModel, pickles));
        }

        return featureGherkins;
    }
}