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