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
package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.spec.Scenario;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

class ScenarioAdapter {
    private ModelAdapter<ScenarioModel> modelAdapter;
    private BehaviourAdapter behaviourAdapter;
    private TypeAdapter typeAdapter;

    ScenarioAdapter(ModelAdapter<ScenarioModel> modelAdapter, BehaviourAdapter behaviourAdapter, TypeAdapter typeAdapter) {
        this.modelAdapter = modelAdapter;
        this.behaviourAdapter = behaviourAdapter;
        this.typeAdapter = typeAdapter;
    }

    List<ScenarioModel> scenarios(List<? extends Element> elements) {

        List<ScenarioModel> scenarioModels = new ArrayList<>();

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String type = typeAdapter.getGherkinType(element.getAnnotationMirrors());
                if (type != null && type.equals(Scenario.class.getName())) {
                    ScenarioModel scenarioModel = modelAdapter.getModel(element, ScenarioModel.class);
                    scenarioModel.setBehaviours(behaviourAdapter.behaviours(element.getEnclosedElements()));
                    scenarioModel.setClassName(typeAdapter.getName(element));
                    scenarioModel.setPackageName(typeAdapter.getPackage(element));
                    scenarioModels.add(scenarioModel);
                }
            }
        }

        return scenarioModels;
    }
}
