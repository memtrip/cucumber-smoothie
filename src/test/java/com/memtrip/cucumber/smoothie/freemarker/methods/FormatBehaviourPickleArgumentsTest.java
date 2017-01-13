package com.memtrip.cucumber.smoothie.freemarker.methods;

import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickleArgument;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormatBehaviourPickleArgumentsTest {

    @Test
    public void getDateArgument() throws Exception {

        // given
        List<BehaviourPickleArgument> behaviourPickleArguments = new ArrayList<>();
        {
            BehaviourPickleArgument argument = new BehaviourPickleArgument();
            argument.setKey("dob");
            argument.setValue("2016-12-12");
            argument.setType(BehaviourPickleArgument.Type.DATE);
            behaviourPickleArguments.add(argument);
        }

        // when
        String result = new FormatBehaviourPickleArguments().formatArgs(behaviourPickleArguments);

        // then
        assertEquals("new SimpleDateFormat(\"YYYY-MM-DD\").parse(\"2016-12-12\")", result);
    }

    @Test
    public void getStringArgument() throws Exception {

        // given
        List<BehaviourPickleArgument> behaviourPickleArguments = new ArrayList<>();
        {
            BehaviourPickleArgument argument = new BehaviourPickleArgument();
            argument.setKey("name");
            argument.setValue("Nelly");
            argument.setType(BehaviourPickleArgument.Type.STRING);
            behaviourPickleArguments.add(argument);
        }

        // when
        String result = new FormatBehaviourPickleArguments().formatArgs(behaviourPickleArguments);

        // then
        assertEquals("\"Nelly\"", result);
    }

    @Test
    public void getCharArgument() throws Exception {

        // given
        List<BehaviourPickleArgument> behaviourPickleArguments = new ArrayList<>();
        {
            BehaviourPickleArgument argument = new BehaviourPickleArgument();
            argument.setKey("name");
            argument.setValue("Nelly");
            argument.setType(BehaviourPickleArgument.Type.CHAR);
            behaviourPickleArguments.add(argument);
        }

        // when
        String result = new FormatBehaviourPickleArguments().formatArgs(behaviourPickleArguments);

        // then
        assertEquals("\'Nelly\'", result);
    }

    @Test
    public void getDoubleArgument() throws Exception {

        // given
        List<BehaviourPickleArgument> behaviourPickleArguments = new ArrayList<>();
        {
            BehaviourPickleArgument argument = new BehaviourPickleArgument();
            argument.setKey("weight");
            argument.setValue("3.12");
            argument.setType(BehaviourPickleArgument.Type.DOUBLE);
            behaviourPickleArguments.add(argument);
        }

        // when
        String result = new FormatBehaviourPickleArguments().formatArgs(behaviourPickleArguments);

        // then
        assertEquals("3.12", result);
    }

    @Test
    public void getBooleanArgument() throws Exception {

        // given
        List<BehaviourPickleArgument> behaviourPickleArguments = new ArrayList<>();
        {
            BehaviourPickleArgument argument = new BehaviourPickleArgument();
            argument.setKey("alive");
            argument.setValue("true");
            argument.setType(BehaviourPickleArgument.Type.BOOLEAN);
            behaviourPickleArguments.add(argument);
        }

        // when
        String result = new FormatBehaviourPickleArguments().formatArgs(behaviourPickleArguments);

        // then
        assertEquals("true", result);
    }

    @Test
    public void getIntArgument() throws Exception {

        // given
        List<BehaviourPickleArgument> behaviourPickleArguments = new ArrayList<>();
        {
            BehaviourPickleArgument argument = new BehaviourPickleArgument();
            argument.setKey("age");
            argument.setValue("11");
            argument.setType(BehaviourPickleArgument.Type.INT);
            behaviourPickleArguments.add(argument);
        }

        // when
        String result = new FormatBehaviourPickleArguments().formatArgs(behaviourPickleArguments);

        // then
        assertEquals("11", result);
    }

    @Test
    public void getLongArgument() throws Exception {

        // given
        List<BehaviourPickleArgument> behaviourPickleArguments = new ArrayList<>();
        {
            BehaviourPickleArgument argument = new BehaviourPickleArgument();
            argument.setKey("miles");
            argument.setValue("19900000000000");
            argument.setType(BehaviourPickleArgument.Type.LONG);
            behaviourPickleArguments.add(argument);
        }

        // when
        String result = new FormatBehaviourPickleArguments().formatArgs(behaviourPickleArguments);

        // then
        assertEquals("19900000000000L", result);
    }
}