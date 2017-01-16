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
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickle;
import com.memtrip.cucumber.smoothie.gherkin.model.ScenarioPickle;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleLocation;
import gherkin.pickles.PickleStep;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScenarioPickleAdapterTest {
    private Pickle pickle1;
    private Pickle pickle2;

    @Before
    public void setup() {

        pickle1 = new Pickle("feeding a suckler cow",
                new ArrayList<PickleStep>(), null, new ArrayList<PickleLocation>());

        pickle2 = new Pickle("feeding a grown cow",
                new ArrayList<PickleStep>(), null, new ArrayList<PickleLocation>());
    }

    @Test
    public void getScenarioPicklesWithoutBackground() {
        List<BehaviourPickle> behaviourPickles1 = new ArrayList<>();
        {
            BehaviourPickle behaviourPickle1 = new BehaviourPickle();
            behaviourPickle1.setMethodName("the_cow_alive_is_true");
            behaviourPickle1.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle1);

            BehaviourPickle behaviourPickle2 = new BehaviourPickle();
            behaviourPickle2.setMethodName("the_protein_should_be");
            behaviourPickle2.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle2);
        }

        List<BehaviourPickle> behaviourPickles2 = new ArrayList<>();
        {
            BehaviourPickle behaviourPickle1 = new BehaviourPickle();
            behaviourPickle1.setMethodName("the_cow_is_in_field");
            behaviourPickle1.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle1);

            BehaviourPickle behaviourPickle2 = new BehaviourPickle();
            behaviourPickle2.setMethodName("the_cow_has_eaten_molasses");
            behaviourPickle2.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle2);
        }

        BehaviourPickleAdapter behaviourPickleAdapter = mock(BehaviourPickleAdapter.class);
        when(behaviourPickleAdapter.getBehaviourPickles(getBehaviours1(), pickle1)).thenReturn(behaviourPickles1);
        when(behaviourPickleAdapter.getBehaviourPickles(getBehaviours2(), pickle2)).thenReturn(behaviourPickles2);

        ScenarioPickleAdapter scenarioPickleAdapter = new ScenarioPickleAdapter(behaviourPickleAdapter);

        // when
        List<ScenarioPickle> scenarioPickles = scenarioPickleAdapter.getScenarioPickles(
                getScenarioModels(getBehaviours1(), getBehaviours2()), null, getPickles());

        // then
        assertEquals(4, scenarioPickles.size());

        assertEquals(0, scenarioPickles.get(0).getOccurrence());
        assertEquals(1, scenarioPickles.get(1).getOccurrence());
        assertEquals(0, scenarioPickles.get(2).getOccurrence());
        assertEquals(1, scenarioPickles.get(3).getOccurrence());

        assertEquals("feeding_a_suckler_cow", scenarioPickles.get(0).getClassName());
        assertEquals("feeding_a_suckler_cow", scenarioPickles.get(1).getClassName());
        assertEquals("feeding_a_grown_cow", scenarioPickles.get(2).getClassName());
        assertEquals("feeding_a_grown_cow", scenarioPickles.get(3).getClassName());

        assertEquals("com.memtrip.cucumber.smoothie", scenarioPickles.get(0).getPackageName());
        assertEquals("com.memtrip.cucumber.smoothie", scenarioPickles.get(1).getPackageName());
        assertEquals("com.memtrip.cucumber.smoothie2", scenarioPickles.get(2).getPackageName());
        assertEquals("com.memtrip.cucumber.smoothie2", scenarioPickles.get(3).getPackageName());

        assertEquals(behaviourPickles1, scenarioPickles.get(0).getBehaviourPickles());
        assertEquals(behaviourPickles1, scenarioPickles.get(1).getBehaviourPickles());
        assertEquals(behaviourPickles2, scenarioPickles.get(2).getBehaviourPickles());
        assertEquals(behaviourPickles2, scenarioPickles.get(3).getBehaviourPickles());
    }

    @Test
    public void getScenarioPicklesWithBackground() {

        // given
        List<BehaviourModel> backgroundBehaviours = new ArrayList<>();
        {
            BehaviourModel behaviourModel1 = new BehaviourModel();
            behaviourModel1.setMethodName("a_cow_that_was_born_on");
            behaviourModel1.setType(BehaviourModel.Type.GIVEN);
            behaviourModel1.setBackgroundBehaviour(true);
            backgroundBehaviours.add(behaviourModel1);

            BehaviourModel behaviourModel2 = new BehaviourModel();
            behaviourModel2.setMethodName("a_cow_that_continues_to_live_on");
            behaviourModel2.setType(BehaviourModel.Type.AND);
            behaviourModel2.setBackgroundBehaviour(true);
            backgroundBehaviours.add(behaviourModel2);
        }

        BackgroundModel backgroundModel = new BackgroundModel();
        backgroundModel.setClassName("background");
        backgroundModel.setBehaviours(backgroundBehaviours);

        List<BehaviourModel> behaviourModels1 = getBehaviours1();
        behaviourModels1.addAll(backgroundBehaviours);

        List<BehaviourModel> behaviourModels2 = getBehaviours2();
        behaviourModels2.addAll(backgroundBehaviours);

        List<BehaviourPickle> behaviourPickles1 = new ArrayList<>();
        {
            BehaviourPickle behaviourPickle1 = new BehaviourPickle();
            behaviourPickle1.setMethodName("the_cow_alive_is_true");
            behaviourPickle1.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle1);

            BehaviourPickle behaviourPickle2 = new BehaviourPickle();
            behaviourPickle2.setMethodName("the_protein_should_be");
            behaviourPickle2.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle2);
        }

        List<BehaviourPickle> behaviourPickles2 = new ArrayList<>();
        {
            BehaviourPickle behaviourPickle1 = new BehaviourPickle();
            behaviourPickle1.setMethodName("the_cow_is_in_field");
            behaviourPickle1.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle1);

            BehaviourPickle behaviourPickle2 = new BehaviourPickle();
            behaviourPickle2.setMethodName("the_cow_has_eaten_molasses");
            behaviourPickle2.setBackgroundBehaviour(false);
            behaviourPickles1.add(behaviourPickle2);
        }

        BehaviourPickleAdapter behaviourPickleAdapter = mock(BehaviourPickleAdapter.class);
        when(behaviourPickleAdapter.getBehaviourPickles(behaviourModels1, pickle1)).thenReturn(behaviourPickles1);
        when(behaviourPickleAdapter.getBehaviourPickles(behaviourModels2, pickle2)).thenReturn(behaviourPickles2);

        ScenarioPickleAdapter scenarioPickleAdapter = new ScenarioPickleAdapter(behaviourPickleAdapter);

        // when
        List<ScenarioPickle> scenarioPickles = scenarioPickleAdapter.getScenarioPickles(
                getScenarioModels(behaviourModels1, behaviourModels2), backgroundModel, getPickles());

        // then
        assertEquals(4, scenarioPickles.size());

        assertEquals(0, scenarioPickles.get(0).getOccurrence());
        assertEquals(1, scenarioPickles.get(1).getOccurrence());
        assertEquals(0, scenarioPickles.get(2).getOccurrence());
        assertEquals(1, scenarioPickles.get(3).getOccurrence());

        assertEquals("feeding_a_suckler_cow", scenarioPickles.get(0).getClassName());
        assertEquals("feeding_a_suckler_cow", scenarioPickles.get(1).getClassName());
        assertEquals("feeding_a_grown_cow", scenarioPickles.get(2).getClassName());
        assertEquals("feeding_a_grown_cow", scenarioPickles.get(3).getClassName());

        assertEquals("com.memtrip.cucumber.smoothie", scenarioPickles.get(0).getPackageName());
        assertEquals("com.memtrip.cucumber.smoothie", scenarioPickles.get(1).getPackageName());
        assertEquals("com.memtrip.cucumber.smoothie2", scenarioPickles.get(2).getPackageName());
        assertEquals("com.memtrip.cucumber.smoothie2", scenarioPickles.get(3).getPackageName());

        assertEquals(behaviourPickles1, scenarioPickles.get(0).getBehaviourPickles());
        assertEquals(behaviourPickles1, scenarioPickles.get(1).getBehaviourPickles());
        assertEquals(behaviourPickles2, scenarioPickles.get(2).getBehaviourPickles());
        assertEquals(behaviourPickles2, scenarioPickles.get(3).getBehaviourPickles());
    }

    private List<BehaviourModel> getBehaviours1() {

        List<BehaviourModel> behaviours = new ArrayList<>();

        BehaviourModel behaviourModel1 = new BehaviourModel();
        behaviourModel1.setMethodName("the_cow_alive_is_true");
        behaviourModel1.setType(BehaviourModel.Type.GIVEN);
        behaviourModel1.setBackgroundBehaviour(true);
        behaviours.add(behaviourModel1);

        BehaviourModel behaviourModel2 = new BehaviourModel();
        behaviourModel2.setMethodName("the_protein_should_be");
        behaviourModel2.setType(BehaviourModel.Type.AND);
        behaviourModel2.setBackgroundBehaviour(true);
        behaviours.add(behaviourModel2);

        return behaviours;
    }

    private List<BehaviourModel> getBehaviours2() {

        List<BehaviourModel> behaviours = new ArrayList<>();

        BehaviourModel behaviourModel1 = new BehaviourModel();
        behaviourModel1.setMethodName("the_cow_is_in_field");
        behaviourModel1.setType(BehaviourModel.Type.GIVEN);
        behaviourModel1.setBackgroundBehaviour(true);
        behaviours.add(behaviourModel1);

        BehaviourModel behaviourModel2 = new BehaviourModel();
        behaviourModel2.setMethodName("the_cow_has_eaten_molasses");
        behaviourModel2.setType(BehaviourModel.Type.AND);
        behaviourModel2.setBackgroundBehaviour(true);
        behaviours.add(behaviourModel2);

        return behaviours;
    }

    private List<ScenarioModel> getScenarioModels(List<BehaviourModel> behaviourModels1, List<BehaviourModel> behaviourModels2) {

        List<ScenarioModel> scenarioModels = new ArrayList<>();

        ScenarioModel scenarioModel1 = new ScenarioModel();
        scenarioModel1.setClassName("feeding_a_suckler_cow");
        scenarioModel1.setPackageName("com.memtrip.cucumber.smoothie");
        scenarioModel1.setValue("feeding a suckler cow");
        scenarioModel1.setBehaviours(behaviourModels1);

        ScenarioModel scenarioModel2 = new ScenarioModel();
        scenarioModel2.setClassName("feeding_a_grown_cow");
        scenarioModel2.setPackageName("com.memtrip.cucumber.smoothie2");
        scenarioModel2.setValue("feeding a grown cow");
        scenarioModel2.setBehaviours(behaviourModels2);

        scenarioModels.add(scenarioModel1);
        scenarioModels.add(scenarioModel2);

        return scenarioModels;
    }

    private List<Pickle> getPickles() {

        List<Pickle> pickles = new ArrayList<>();

        pickles.add(pickle1);

        pickles.add(pickle1);

        pickles.add(pickle2);

        pickles.add(pickle2);

        return pickles;
    }
}
