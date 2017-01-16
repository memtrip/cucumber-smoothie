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
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArgumentAdapterTest {

    private ArgumentTypeMatcher argumentTypeMatcher;

    @Before
    public void setup() {
        argumentTypeMatcher = new ArgumentTypeMatcher();
    }

    @Test
    public void oneInt() {

        // given
        String behaviourValue = "the cow's weight $n kg";
        String pickleValue = "the cow's weight 450 kg";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("n", args.get(0).getKey());
        assertEquals("450", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.INT, args.get(0).getType());
    }

    @Test
    public void oneIntWithEscapedDollarSign() {

        // given
        String behaviourValue = "the cow's value is $$n";
        String pickleValue = "the cow's weight $500";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("n", args.get(0).getKey());
        assertEquals("500", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.INT, args.get(0).getType());
    }

    @Test
    public void oneDouble() {

        // given
        String behaviourValue = "the cow's weight is $weight kg";
        String pickleValue = "the cow's weighs 7.12 kg";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("weight", args.get(0).getKey());
        assertEquals("7.12", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.DOUBLE, args.get(0).getType());
    }

    @Test
    public void oneBoolean() {

        // given
        String behaviourValue = "It's true the cow alive state is $alive";
        String pickleValue = "It's true the cow alive state is true";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("alive", args.get(0).getKey());
        assertEquals("true", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.BOOLEAN, args.get(0).getType());
    }

    @Test
    public void oneLong() {

        // given
        String behaviourValue = "It's true the cow is $years years old";
        String pickleValue = "It's true the cow is 19000000000000000000 years old";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("years", args.get(0).getKey());
        assertEquals("19000000000000000000", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.LONG, args.get(0).getType());
    }

    @Test
    public void oneDate() {

        // given
        String behaviourValue = "The cow was born on $dob";
        String pickleValue = "The cow was born on 2016-06-27";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("dob", args.get(0).getKey());
        assertEquals("2016-06-27", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.DATE, args.get(0).getType());
    }

    @Test
    public void twoChars() {
        // given
        String behaviourValue = "The initials of the cow are $first_initial.$second_initial";
        String pickleValue = "The initials of the cow are 'S'.'K'";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(2, args.size());
        assertEquals("first_initial", args.get(0).getKey());
        assertEquals("S", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.CHAR, args.get(0).getType());

        assertEquals("second_initial", args.get(1).getKey());
        assertEquals("K", args.get(1).getValue());
        assertEquals(BehaviourPickleArgument.Type.CHAR, args.get(1).getType());
    }

    @Test
    public void oneStringFrom() {

        // given
        String behaviourValue = "the cows name is $name";
        String pickleValue = "the cows name is \"Samuel Kirton\"";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("name", args.get(0).getKey());
        assertEquals("Samuel Kirton", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(0).getType());
    }

    @Test
    public void twoStrings() {

        // given
        String behaviourValue = "the cow's name is $name, the cow says $say";
        String pickleValue = "the cow's name is \"Samuel Kirton\", the cow says \"moooooooooo\"";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(2, args.size());
        assertEquals("name", args.get(0).getKey());
        assertEquals("Samuel Kirton", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(0).getType());

        assertEquals("say", args.get(1).getKey());
        assertEquals("moooooooooo", args.get(1).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(1).getType());
    }

    @Test
    public void oneIntAndOneStringOneDouble() {
        // given
        String behaviourValue = "the cow's weighs $weight kg, the cow says $say, the cow wants £$pounds";
        String pickleValue = "the cow's weighs 23 kg, the cow says \"whhhhhyyy?\", the cow wants £1.7";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(3, args.size());
        assertEquals("weight", args.get(0).getKey());
        assertEquals("23", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.INT, args.get(0).getType());

        assertEquals("say", args.get(1).getKey());
        assertEquals("whhhhhyyy?", args.get(1).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(1).getType());

        assertEquals("pounds", args.get(2).getKey());
        assertEquals("1.7", args.get(2).getValue());
        assertEquals(BehaviourPickleArgument.Type.DOUBLE, args.get(2).getType());
    }

    /**
     * The following examples are testing step definitions from gherkin "Examples:" mapping
     */

    @Test
    public void oneIntFromExamples() {

        // given
        String behaviourValue = "the cow's weight <weight> kg";
        String pickleValue = "the cow's weight 450 kg";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("weight", args.get(0).getKey());
        assertEquals("450", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.INT, args.get(0).getType());
    }

    @Test
    public void oneDoubleFromExamples() {

        // given
        String behaviourValue = "the cow's weight is <weight> kg";
        String pickleValue = "the cow's weighs 7.12 kg";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("weight", args.get(0).getKey());
        assertEquals("7.12", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.DOUBLE, args.get(0).getType());
    }

    @Test
    public void oneBooleanFromExamples() {

        // given
        String behaviourValue = "It's true the cow alive state is <alive>";
        String pickleValue = "It's true the cow alive state is true";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("alive", args.get(0).getKey());
        assertEquals("true", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.BOOLEAN, args.get(0).getType());
    }

    @Test
    public void oneLongFromExamples() {

        // given
        String behaviourValue = "It's true the cow is <years> years old";
        String pickleValue = "It's true the cow is 19000000000000000000 years old";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("years", args.get(0).getKey());
        assertEquals("19000000000000000000", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.LONG, args.get(0).getType());
    }

    @Test
    public void oneDateFromExamples() {

        // given
        String behaviourValue = "The cow was born on <dob>";
        String pickleValue = "The cow was born on 2016-06-27";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("dob", args.get(0).getKey());
        assertEquals("2016-06-27", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.DATE, args.get(0).getType());
    }

    @Test
    public void twoCharsFromExamples() {
        // given
        String behaviourValue = "The initials of the cow are <first_initial>.<second_initial>";
        String pickleValue = "The initials of the cow are 'S'.'K'";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(2, args.size());
        assertEquals("first_initial", args.get(0).getKey());
        assertEquals("S", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.CHAR, args.get(0).getType());

        assertEquals("second_initial", args.get(1).getKey());
        assertEquals("K", args.get(1).getValue());
        assertEquals(BehaviourPickleArgument.Type.CHAR, args.get(1).getType());
    }

    @Test
    public void oneStringFromExamples() {

        // given
        String behaviourValue = "the cows name is <name>";
        String pickleValue = "the cows name is \"Samuel Kirton\"";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(1, args.size());
        assertEquals("name", args.get(0).getKey());
        assertEquals("Samuel Kirton", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(0).getType());
    }

    @Test
    public void twoStringsFromExamples() {

        // given
        String behaviourValue = "the cow's name is <name>, the cow says <say>";
        String pickleValue = "the cow's name is \"Samuel Kirton\", the cow says \"moooooooooo\"";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(2, args.size());
        assertEquals("name", args.get(0).getKey());
        assertEquals("Samuel Kirton", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(0).getType());

        assertEquals("say", args.get(1).getKey());
        assertEquals("moooooooooo", args.get(1).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(1).getType());
    }

    @Test
    public void oneIntAndOneStringOneDoubleFromExamples() {
        // given
        String behaviourValue = "the cow's weighs <weight> kg, the cow says <say>, the cow wants £<pounds>";
        String pickleValue = "the cow's weighs 23 kg, the cow says \"whhhhhyyy?\", the cow wants £1.7";

        // when
        ArgumentAdapter argumentAdapter = new ArgumentAdapter(argumentTypeMatcher);

        // then
        List<BehaviourPickleArgument> args =
                argumentAdapter.getArgumentsFromPickleValue(behaviourValue, pickleValue);

        assertEquals(3, args.size());
        assertEquals("weight", args.get(0).getKey());
        assertEquals("23", args.get(0).getValue());
        assertEquals(BehaviourPickleArgument.Type.INT, args.get(0).getType());

        assertEquals("say", args.get(1).getKey());
        assertEquals("whhhhhyyy?", args.get(1).getValue());
        assertEquals(BehaviourPickleArgument.Type.STRING, args.get(1).getType());

        assertEquals("pounds", args.get(2).getKey());
        assertEquals("1.7", args.get(2).getValue());
        assertEquals(BehaviourPickleArgument.Type.DOUBLE, args.get(2).getType());
    }
}