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

import com.memtrip.cucumber.smoothie.Log;
import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.Set;

public class AnnotationAdapter {

    public List<FeatureModel> getFeatureAnnotations(Set<? extends Element> elements) {

        Log.note("Processing gherkin bindings...");

        ValueAdapter valueAdapter = new ValueAdapter();

        TypeAdapter typeAdapter = new TypeAdapter();

        BehaviourAdapter behaviourAdapter = new BehaviourAdapter(new ModelAdapter<BehaviourModel>(valueAdapter), typeAdapter);

        ScenarioAdapter scenarioAdapter =
                new ScenarioAdapter(new ModelAdapter<ScenarioModel>(valueAdapter), behaviourAdapter, typeAdapter);

        BackgroundAdapter backgroundAdapter =
                new BackgroundAdapter(new ModelAdapter<BackgroundModel>(valueAdapter), behaviourAdapter, typeAdapter);

        FeatureAdapter featureAdapter =
                new FeatureAdapter(valueAdapter, scenarioAdapter, backgroundAdapter, typeAdapter);

        Log.note(elements.size() + " bindings processed.");

        return featureAdapter.features(elements);
    }
}