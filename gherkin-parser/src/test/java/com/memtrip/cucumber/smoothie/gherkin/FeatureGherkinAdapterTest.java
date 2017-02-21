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
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import com.memtrip.cucumber.smoothie.gherkin.model.ScenarioPickle;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleLocation;
import gherkin.pickles.PickleStep;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeatureGherkinAdapterTest {

    @Test
    public void getFeatureGherkinWithoutBackground() {

        // given
        List<ScenarioModel> scenarios = new ArrayList<>();
        {
            ScenarioModel scenarioModel = new ScenarioModel();
            scenarioModel.setClassName("feeding_a_suckler_cow");
            scenarioModel.setValue("feeding a suckler cow");
            scenarioModel.setPackageName("com.memtrip.cucumber.smoothie");
            scenarios.add(scenarioModel);
        }

        List<Pickle> pickles = new ArrayList<>();
        {
            Pickle pickle1 = new Pickle("feeding a suckler cow",
                    new ArrayList<PickleStep>(), null, new ArrayList<PickleLocation>());
            pickles.add(pickle1);

            Pickle pickle2 = new Pickle("feeding a grown cow",
                    new ArrayList<PickleStep>(), null, new ArrayList<PickleLocation>());
            pickles.add(pickle2);
        }

        List<ScenarioPickle> scenarioPickles = new ArrayList<>();

        ScenarioPickleAdapter scenarioPickleAdapter = mock(ScenarioPickleAdapter.class);
        when(scenarioPickleAdapter.getScenarioPickles(scenarios, null, pickles)).thenReturn(scenarioPickles);

        FeatureGherkinAdapter featureGherkinAdapter = new FeatureGherkinAdapter(scenarioPickleAdapter);

        FeatureModel featureModel = new FeatureModel();
        featureModel.setClassName("Cow");
        featureModel.setScenarios(scenarios);
        featureModel.setOneShot(true);

        // when
        FeatureGherkin featureGherkin = featureGherkinAdapter.getFeatureGherkin(featureModel, pickles);

        // then
        assertEquals("Cow", featureGherkin.getClassName());
        assertTrue(featureGherkin.isOneShot());
        assertNotNull(featureGherkin.getScenarioPickles());
    }

    @Test
    public void getFeatureGherkinWithBackground() {

        // given
        List<ScenarioModel> scenarios = new ArrayList<>();
        {
            ScenarioModel scenarioModel = new ScenarioModel();
            scenarioModel.setClassName("feeding_a_suckler_cow");
            scenarioModel.setValue("feeding a suckler cow");
            scenarioModel.setPackageName("com.memtrip.cucumber.smoothie");
            scenarios.add(scenarioModel);
        }

        List<Pickle> pickles = new ArrayList<>();
        {
            Pickle pickle1 = new Pickle("feeding a suckler cow",
                    new ArrayList<PickleStep>(), null, new ArrayList<PickleLocation>());
            pickles.add(pickle1);

            Pickle pickle2 = new Pickle("feeding a grown cow",
                    new ArrayList<PickleStep>(), null, new ArrayList<PickleLocation>());
            pickles.add(pickle2);
        }

        List<ScenarioPickle> scenarioPickles = new ArrayList<>();

        ScenarioPickleAdapter scenarioPickleAdapter = mock(ScenarioPickleAdapter.class);
        when(scenarioPickleAdapter.getScenarioPickles(scenarios, null, pickles)).thenReturn(scenarioPickles);
        FeatureGherkinAdapter featureGherkinAdapter = new FeatureGherkinAdapter(scenarioPickleAdapter);

        BackgroundModel backgroundModel = new BackgroundModel();
        backgroundModel.setClassName("background");
        backgroundModel.setPackageName("com.memtrip.cucumber.smoothie");

        FeatureModel featureModel = new FeatureModel();
        featureModel.setClassName("Cow");
        featureModel.setScenarios(scenarios);
        featureModel.setBackground(backgroundModel);

        // when
        FeatureGherkin featureGherkin = featureGherkinAdapter.getFeatureGherkin(featureModel, pickles);

        // then
        assertEquals("Cow", featureGherkin.getClassName());
        assertNotNull(featureGherkin.getScenarioPickles());
        assertTrue(featureGherkin.hasBackground());
        assertEquals("background", featureGherkin.getBackground().getClassName());
        assertEquals("com.memtrip.cucumber.smoothie", featureGherkin.getBackground().getPackageName());
    }
}
