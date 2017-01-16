/**
 * Copyright 2013-present memtrip LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
