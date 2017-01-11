package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;

import com.memtrip.cucumber.smoothie.gherkin.model.*;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.ArrayList;
import java.util.List;

public class GherkinAdapter {
    private GherkinParser gherkinParser;
    private ArgumentAdapter argumentAdapter;
    private FileUtil fileUtil;

    public GherkinAdapter(GherkinParser gherkinParser, ArgumentAdapter argumentAdapter, FileUtil fileUtil) {
        this.gherkinParser = gherkinParser;
        this.argumentAdapter = argumentAdapter;
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
        for (ScenarioModel scenarioModel : getScenariosWithBackground(featureModel)) {
            for (Pickle pickle : pickles) {
                ScenarioPickle scenarioPickle = new ScenarioPickle();
                scenarioPickle.setClassName(scenarioModel.getClassName());
                scenarioPickle.setBehaviourPickles(behaviourPickles(scenarioModel.getBehaviours(), pickle));
                scenarioPickles.add(scenarioPickle);
            }
        }

        return scenarioPickles;
    }

    private List<ScenarioModel> getScenariosWithBackground(FeatureModel featureModel) {
        List<ScenarioModel> scenarioModels = new ArrayList<>();

        if (featureModel.getBackground() != null) {
            scenarioModels.add(convertBackgroundToScenarioModel(featureModel.getBackground()));
        }

        scenarioModels.addAll(featureModel.getScenarios());

        return scenarioModels;
    }

    private ScenarioModel convertBackgroundToScenarioModel(BackgroundModel backgroundModel) {
        ScenarioModel scenarioModel = new ScenarioModel();
        scenarioModel.setClassName(backgroundModel.getClassName());
        scenarioModel.setBehaviours(backgroundModel.getBehaviours());
        return scenarioModel;
    }

    private List<BehaviourPickle> behaviourPickles(List<BehaviourModel> behaviours, Pickle pickle) {

        List<BehaviourPickle> behaviourPickles = new ArrayList<>();

        for (int i = 0; i < behaviours.size(); i++) {
            BehaviourModel behaviourModel = behaviours.get(i);
            PickleStep pickleStep = pickle.getSteps().get(i);

            List<BehaviourPickleArgument> arguments = argumentAdapter.getArgumentsFromPickleValue(
                    behaviourModel.getValue(),
                    pickleStep.getText()
            );

            BehaviourPickle behaviourPickle = new BehaviourPickle();
            behaviourPickle.setMethodName(behaviourModel.getMethodName());
            behaviourPickle.setArguments(arguments);

            behaviourPickles.add(behaviourPickle);
        }

        return behaviourPickles;
    }

    private List<Pickle> getPickles(FeatureModel featureModel) {
        String file = fileUtil.getFile(featureModel.getValue());
        return gherkinParser.getPickles(file);
    }
}