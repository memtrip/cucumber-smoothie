package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickleArgument;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentTypeMatcherTests {

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
}