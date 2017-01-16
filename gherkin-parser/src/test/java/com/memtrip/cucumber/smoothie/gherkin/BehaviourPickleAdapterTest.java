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
import gherkin.pickles.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BehaviourPickleAdapterTest {

    @Test
    public void getBehaviourPickles() {

        // given
        List<BehaviourModel> behaviourModels = new ArrayList<>();
        {
            BehaviourModel behaviourModel1 = new BehaviourModel();
            behaviourModel1.setMethodName("the_cow_weighs_x_kg");
            behaviourModel1.setType(BehaviourModel.Type.GIVEN);
            behaviourModel1.setValue("the cow weighs <weight> kg");
            behaviourModel1.setBackgroundBehaviour(false);
            behaviourModels.add(behaviourModel1);

            BehaviourModel behaviourModel2 = new BehaviourModel();
            behaviourModel2.setMethodName("we_calculate_the_feeding_requirements");
            behaviourModel2.setType(BehaviourModel.Type.AND);
            behaviourModel2.setValue("we calculate the feeding requirements");
            behaviourModel2.setBackgroundBehaviour(true);
            behaviourModels.add(behaviourModel2);
        }

        Pickle pickle;
        {
            List<PickleStep> pickleSteps = new ArrayList<>();
            PickleStep pickleStep1 = new PickleStep("the cow weighs 450 kg",
                    new ArrayList<Argument>(), new ArrayList<PickleLocation>());
            pickleSteps.add(pickleStep1);

            PickleStep pickleStep2 = new PickleStep("we calculate the feeding requirements",
                    new ArrayList<Argument>(), new ArrayList<PickleLocation>());
            pickleSteps.add(pickleStep2);

            pickle = new Pickle(
                    "feeding a suckler cow",
                    pickleSteps,
                    new ArrayList<PickleTag>(),
                    new ArrayList<PickleLocation>()
            );
        }

        ArgumentAdapter argumentAdapter = mock(ArgumentAdapter.class);
        when(argumentAdapter.getArgumentsFromPickleValue("the cow weighs <weight> kg",
                "the cow weighs 450 kg")).thenReturn(getBehaviourPickleArguments());

        when(argumentAdapter.getArgumentsFromPickleValue("we calculate the feeding requirements",
                "we calculate the feeding requirements")).thenReturn(new ArrayList<BehaviourPickleArgument>());

        BehaviourPickleAdapter behaviourPickleAdapter = new BehaviourPickleAdapter(argumentAdapter);

        // when
        List<BehaviourPickle> behaviourPickles = behaviourPickleAdapter.getBehaviourPickles(behaviourModels, pickle);

        // then
        assertEquals("the_cow_weighs_x_kg", behaviourPickles.get(0).getMethodName());
        assertFalse(behaviourPickles.get(0).isBackgroundBehaviour());
        assertEquals(getBehaviourPickleArguments(), behaviourPickles.get(0).getArguments());

        assertEquals("we_calculate_the_feeding_requirements", behaviourPickles.get(1).getMethodName());
        assertTrue(behaviourPickles.get(1).isBackgroundBehaviour());
        assertEquals(new ArrayList<BehaviourPickleArgument>(), behaviourPickles.get(1).getArguments());
    }

    private List<BehaviourPickleArgument> getBehaviourPickleArguments() {

        List<BehaviourPickleArgument> arguments = new ArrayList<>();

        BehaviourPickleArgument behaviourPickleArgument = new BehaviourPickleArgument();
        behaviourPickleArgument.setKey("weight");
        behaviourPickleArgument.setValue("450");
        behaviourPickleArgument.setType(BehaviourPickleArgument.Type.INT);
        arguments.add(behaviourPickleArgument);

        return arguments;
    }
}
