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

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.gherkin.model.ScenarioPickle;
import gherkin.pickles.Pickle;

import java.util.ArrayList;
import java.util.List;

class ScenarioPickleAdapter {

    private BehaviourPickleAdapter behaviourPickleAdapter;
    private TagAdapter tagAdapter;

    ScenarioPickleAdapter(BehaviourPickleAdapter behaviourPickleAdapter, TagAdapter tagAdapter) {
        this.behaviourPickleAdapter = behaviourPickleAdapter;
        this.tagAdapter = tagAdapter;
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
                    scenarioPickle.setTags(tagAdapter.getTags(pickle));
                    scenarioPickles.add(scenarioPickle);

                    occurrence++;
                }
            }
        }

        return scenarioPickles;
    }
}
