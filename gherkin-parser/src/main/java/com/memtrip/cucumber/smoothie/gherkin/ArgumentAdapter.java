package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickleArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

class ArgumentAdapter {

    private ArgumentTypeMatcher argumentTypeMatcher;

    ArgumentAdapter(ArgumentTypeMatcher argumentTypeMatcher) {
        this.argumentTypeMatcher = argumentTypeMatcher;
    }

    List<BehaviourPickleArgument> getArgumentsFromPickleValue(String behaviourValue, String pickleValue) {

        List<BehaviourPickleArgument> arguments = new ArrayList<>();

        String[] behaviourPieces = argumentTypeMatcher.removeArgumentKeysFromString(behaviourValue);
        List<String> argumentIndicators = argumentTypeMatcher.getArgumentKeys(behaviourValue);
        String pickleArgumentString = removeBehaviourTextFromPickleString(behaviourPieces, pickleValue);

        Matcher matcher = argumentTypeMatcher.matchArguments(pickleArgumentString);

        boolean result = matcher.find();

        if (result) {

            StringBuffer sb = new StringBuffer();

            int position = 0;

            do {
                String argument = matcher.group();

                BehaviourPickleArgument behaviourPickleArgument = new BehaviourPickleArgument();
                behaviourPickleArgument.setValue(removeLiteralQuotes(argument));
                behaviourPickleArgument.setType(argumentTypeMatcher.getType(argument));
                behaviourPickleArgument.setKey(argumentIndicators.get(position));

                arguments.add(behaviourPickleArgument);

                matcher.appendReplacement(sb, "");
                result = matcher.find();

                position++;

            } while (result);

            matcher.appendTail(sb);
        }

        return arguments;
    }

    private String removeBehaviourTextFromPickleString(String[] behaviourPieces, String pickleValue) {

        for (String behaviourPiece : behaviourPieces) {
            pickleValue = pickleValue.replace(behaviourPiece, " ");
        }

        return pickleValue;
    }

    private String removeLiteralQuotes(String literal) {
        return literal.replace("\"","").replace("\'","");
    }
}