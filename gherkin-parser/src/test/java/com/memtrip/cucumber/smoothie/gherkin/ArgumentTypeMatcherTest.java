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

import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickleArgument;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArgumentTypeMatcherTest {

    @Test
    public void getType() {

        // given
        ArgumentTypeMatcher matcher = new ArgumentTypeMatcher();

        // then
        assertEquals(BehaviourPickleArgument.Type.DATE, matcher.getType("2016-10-28"));
        assertEquals(BehaviourPickleArgument.Type.STRING, matcher.getType("\"hello\""));
        assertEquals(BehaviourPickleArgument.Type.CHAR, matcher.getType("\'C\'"));
        assertEquals(BehaviourPickleArgument.Type.DOUBLE, matcher.getType("3.5"));
        assertEquals(BehaviourPickleArgument.Type.BOOLEAN, matcher.getType("true"));
        assertEquals(BehaviourPickleArgument.Type.INT, matcher.getType("15"));
        assertEquals(BehaviourPickleArgument.Type.LONG, matcher.getType("19000000000000000000"));
    }

    @Test
    public void getArgumentKeys() {

        // given
        ArgumentTypeMatcher matcher = new ArgumentTypeMatcher();

        // then
        List<String> keys = matcher.getArgumentKeys("There are $n argument keys with <name>");

        assertEquals(2, keys.size());
        assertEquals("n", keys.get(0));
        assertEquals("name", keys.get(1));
    }
}