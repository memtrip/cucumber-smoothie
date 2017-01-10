package com.memtrip.cucumber.smoothie.gherkin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentTypeMatcherTests {

    @Test
    public void getType() {

        // given
        ArgumentTypeMatcher matcher = new ArgumentTypeMatcher();

        // then
        assertEquals(BehaviourArgument.Type.DATE, matcher.getType("2016-10-28"));
        assertEquals(BehaviourArgument.Type.STRING, matcher.getType("\"hello\""));
        assertEquals(BehaviourArgument.Type.CHAR, matcher.getType("\'C\'"));
        assertEquals(BehaviourArgument.Type.DOUBLE, matcher.getType("3.5"));
        assertEquals(BehaviourArgument.Type.BOOLEAN, matcher.getType("true"));
        assertEquals(BehaviourArgument.Type.INT, matcher.getType("15"));
        assertEquals(BehaviourArgument.Type.LONG, matcher.getType("19000000000000000000"));
    }
}