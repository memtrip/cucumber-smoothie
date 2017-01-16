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