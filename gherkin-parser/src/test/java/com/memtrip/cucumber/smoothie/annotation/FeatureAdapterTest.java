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

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import org.junit.Test;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class FeatureAdapterTest {

    @Test
    public void features() {

        // given
        List enclosedElements = new ArrayList<>();
        Element element1 = mock(Element.class);
        when(element1.getEnclosedElements()).thenReturn(enclosedElements);

        Set elements = new HashSet<>();
        elements.add(element1);

        ValueAdapter valueAdapter = mock(ValueAdapter.class);
        when(valueAdapter.value(element1, "projectRootFolderName")).thenReturn("gherkin-parser");
        when(valueAdapter.value(element1, "featureFilePath")).thenReturn("src/test/resources/cow.feature");
        when(valueAdapter.value(element1, "oneShot")).thenReturn("true");

        ScenarioModel scenarioModel = new ScenarioModel();
        scenarioModel.setValue("Buy last coffee");

        List<ScenarioModel> scenarios = new ArrayList<>();
        scenarios.add(scenarioModel);

        ScenarioAdapter scenarioAdapter = mock(ScenarioAdapter.class);
        when(scenarioAdapter.scenarios(enclosedElements)).thenReturn(scenarios);

        BehaviourModel givenBehaviour = new BehaviourModel();
        givenBehaviour.setValue("a $100 microwave was sold on 2015-11-03");
        List behaviours = new ArrayList();
        behaviours.add(givenBehaviour);

        BackgroundModel backgroundModel = new BackgroundModel();
        backgroundModel.setBehaviours(behaviours);
        BackgroundAdapter backgroundAdapter = mock(BackgroundAdapter.class);
        when(backgroundAdapter.background(enclosedElements)).thenReturn(backgroundModel);

        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getName(element1)).thenReturn("Serve_coffee");

        // when
        FeatureAdapter featureAdapter = new FeatureAdapter(valueAdapter, scenarioAdapter, backgroundAdapter, typeAdapter);

        // then
        List<FeatureModel> features = featureAdapter.features(elements);
        assertEquals(1, features.size());
        assertEquals("gherkin-parser", features.get(0).getProjectRootFolderName());
        assertEquals("src/test/resources/cow.feature", features.get(0).getFeatureFilePath());
        assertTrue(features.get(0).isOneShot());
        assertEquals("Buy last coffee", features.get(0).getScenarios().get(0).getValue());
        assertEquals("a $100 microwave was sold on 2015-11-03", features.get(0).getBackground().getBehaviours().get(0).getValue());
    }
}