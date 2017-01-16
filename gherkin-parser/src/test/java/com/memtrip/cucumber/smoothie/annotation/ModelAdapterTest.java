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

import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import org.junit.Test;

import javax.lang.model.element.Element;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ModelAdapterTest {

    @Test
    public void getModel() throws Exception {

        // given
        Element element = mock(Element.class);

        ValueAdapter valueAdapter = mock(ValueAdapter.class);
        when(valueAdapter.value(element)).thenReturn("the cow weighs <weight> kg");

        // when
        ModelAdapter<BehaviourModel> modelAdapter = new ModelAdapter<>(valueAdapter);

        // then
        BehaviourModel model = modelAdapter.getModel(element, BehaviourModel.class);

        assertEquals("the cow weighs <weight> kg", model.getValue());
    }
}
