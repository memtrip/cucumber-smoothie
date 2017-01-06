package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import gherkin.ast.Step;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.ArrayList;
import java.util.List;

public class GherkinAdapter {
    private GherkinParser gherkinParser;
    private FileUtil fileUtil;

    public GherkinAdapter(GherkinParser gherkinParser, FileUtil fileUtil) {
        this.gherkinParser = gherkinParser;
        this.fileUtil = fileUtil;
    }

    public FeatureGherkin create(FeatureModel featureModel) {
        FeatureGherkin featureGherkin = new FeatureGherkin();
        featureGherkin.setClassName(featureModel.getClassName());
        featureGherkin.setScenarioPickles(scenarioPickles(featureModel));
        return featureGherkin;
    }

    private List<ScenarioPickle> scenarioPickles(FeatureModel featureModel) {
        List<ScenarioPickle> scenarioPickles = new ArrayList<>();

        List<Pickle> pickles = getPickles(featureModel);
        for (ScenarioModel scenarioModel : featureModel.getScenarios()) {
            for (int i = 0; i < pickles.size(); i++) {
                ScenarioPickle scenarioPickle = new ScenarioPickle();
                scenarioPickle.setClassName(scenarioModel.getClassName() + "_" + i);
                scenarioPickle.setSteps(scenarioSteps(scenarioModel.getBehaviours(), pickles.get(i)));
            }
        }

        return scenarioPickles;
    }

    private List<ScenarioSteps> scenarioSteps(List<BehaviourModel> behaviours, Pickle pickle) {

        List<ScenarioSteps> scenarioSteps = new ArrayList<>();

        for (int i = 0; i < behaviours.size(); i++) {
            BehaviourModel behaviourModel = behaviours.get(i);
            PickleStep pickleStep = pickle.getSteps().get(i);

            System.out.print("");
        }

        return scenarioSteps;
    }

    private List<Pickle> getPickles(FeatureModel featureModel) {
        String file = fileUtil.getFile(featureModel.getValue());
        return gherkinParser.getPickles(file);
    }
}