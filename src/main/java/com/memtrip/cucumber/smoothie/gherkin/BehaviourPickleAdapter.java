package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickle;
import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickleArgument;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleStep;

import java.util.ArrayList;
import java.util.List;

class BehaviourPickleAdapter {

    private ArgumentAdapter argumentAdapter;

    BehaviourPickleAdapter(ArgumentAdapter argumentAdapter) {
        this.argumentAdapter = argumentAdapter;
    }

    List<BehaviourPickle> getBehaviourPickles(List<BehaviourModel> behaviours, Pickle pickle) {

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
            behaviourPickle.setBackgroundBehaviour(behaviourModel.isBackgroundBehaviour());
            behaviourPickle.setArguments(arguments);

            behaviourPickles.add(behaviourPickle);
        }

        return behaviourPickles;
    }
}
