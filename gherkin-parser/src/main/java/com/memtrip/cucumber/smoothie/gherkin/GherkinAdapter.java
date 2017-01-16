package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.Log;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;

import javax.annotation.processing.Filer;
import java.util.ArrayList;
import java.util.List;

public class GherkinAdapter {

    private GherkinParser gherkinParser;

    public GherkinAdapter(Filer filer) {
        gherkinParser = new GherkinParser(new FileUtil(filer), new Compiler(), new Parser<>(new AstBuilder()));
    }

    public List<FeatureGherkin> getFeatureGherkin(List<FeatureModel> featureModels) {

        Log.warning("Binding with feature files...");

        List<FeatureGherkin> featureGherkins = new ArrayList<>();

        ArgumentAdapter argumentAdapter = new ArgumentAdapter(new ArgumentTypeMatcher());
        BehaviourPickleAdapter behaviourPickleAdapter = new BehaviourPickleAdapter(argumentAdapter);
        ScenarioPickleAdapter scenarioPickleAdapter = new ScenarioPickleAdapter(behaviourPickleAdapter);
        FeatureGherkinAdapter featureGherkinAdapter = new FeatureGherkinAdapter(scenarioPickleAdapter);

        for (FeatureModel featureModel : featureModels) {
            List<Pickle> pickles = gherkinParser.getPickles(
                    featureModel.getProjectRootFolderName(),
                    featureModel.getFeatureFilePath()
            );

            featureGherkins.add(featureGherkinAdapter.getFeatureGherkin(featureModel, pickles));
        }

        Log.warning("Bound (" + featureGherkins.size() + ") features.");

        return featureGherkins;
    }
}