package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.gherkin.model.ScenarioPickle;
import gherkin.pickles.Pickle;

import java.util.ArrayList;
import java.util.List;

class ScenarioPickleAdapter {

    private BehaviourPickleAdapter behaviourPickleAdapter;

    ScenarioPickleAdapter(BehaviourPickleAdapter behaviourPickleAdapter) {
        this.behaviourPickleAdapter = behaviourPickleAdapter;
    }

    List<ScenarioPickle> getScenarioPickles(List<ScenarioModel> scenarios, BackgroundModel background, List<Pickle> pickles) {

        if (background != null) {
            for (ScenarioModel scenarioModel : scenarios) {
                scenarioModel.getBehaviours().addAll(0, background.getBehaviours());
            }
        }

        List<ScenarioPickle> scenarioPickles = new ArrayList<>();

        for (ScenarioModel scenarioModel : scenarios) {

            int occurrence = 0;

            for (Pickle pickle : pickles) {
                if (scenarioModel.getValue().equals(pickle.getName())) {
                    ScenarioPickle scenarioPickle = new ScenarioPickle();
                    scenarioPickle.setClassName(scenarioModel.getClassName());
                    scenarioPickle.setPackageName(scenarioModel.getPackageName());
                    scenarioPickle.setOccurrence(occurrence);
                    scenarioPickle.setBehaviourPickles(behaviourPickleAdapter.getBehaviourPickles(
                            scenarioModel.getBehaviours(),
                            pickle));
                    scenarioPickles.add(scenarioPickle);

                    occurrence++;
                }
            }
        }

        return scenarioPickles;
    }
}
