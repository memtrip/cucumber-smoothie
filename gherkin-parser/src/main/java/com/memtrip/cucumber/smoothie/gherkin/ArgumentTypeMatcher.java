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
import java.util.regex.Pattern;

class ArgumentTypeMatcher {
    private Pattern keyPattern;
    private Pattern argumentPattern;
    private Pattern datePattern;
    private Pattern stringPattern;
    private Pattern charPattern;

    private static final String KEY_PATTERN = "<(.*?)>|\\$([a-zA-Z_\\x7f-\\xff][a-zA-Z0-9_\\x7f-\\xff]*)";

    private static final String DATE_MATCHER = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";
    private static final String STRING_MATCHER = "\"(.*?)\"";
    private static final String CHAR_MATCHER = "\'(.*?)\'";
    private static final String NUMBER_MATCHER = "[+-]?([0-9]*[.])?[0-9]+";
    private static final String BOOLEAN_MATCHER = "(true|false)";

    ArgumentTypeMatcher() {

        keyPattern = Pattern.compile(KEY_PATTERN);

        argumentPattern = Pattern.compile(DATE_MATCHER + "|" +
                STRING_MATCHER + "|" +
                CHAR_MATCHER + "|" +
                NUMBER_MATCHER + "|" +
                BOOLEAN_MATCHER);

        datePattern = Pattern.compile(DATE_MATCHER);
        stringPattern = Pattern.compile(STRING_MATCHER);
        charPattern = Pattern.compile(CHAR_MATCHER);
    }

    List<String> getArgumentKeys(String value) {
        List<String> args = new ArrayList<>();

        Matcher matcher = keyPattern.matcher(value);

        while (matcher.find()) {
            String group = matcher.group()
                    .replace("<", "")
                    .replace(">", "")
                    .replace("$", "");

            args.add(group);
        }

        return args;
    }

    String[] removeArgumentKeysFromString(String value) {

        return value
                .replaceAll("  ", " ")
                .split(KEY_PATTERN);
    }

    Matcher matchArguments(String value) {
        return argumentPattern.matcher(value);
    }

    BehaviourPickleArgument.Type getType(String argument) {
        if (isDate(argument)) {
            return BehaviourPickleArgument.Type.DATE;
        } else if (isString(argument)) {
            return BehaviourPickleArgument.Type.STRING;
        } else if (isChar(argument)) {
            return BehaviourPickleArgument.Type.CHAR;
        } else if (isDouble(argument)) {
            return BehaviourPickleArgument.Type.DOUBLE;
        } else if (isBoolean(argument)) {
            return BehaviourPickleArgument.Type.BOOLEAN;
        } else if (isInt(argument)) {
            return BehaviourPickleArgument.Type.INT;
        } else {
            return BehaviourPickleArgument.Type.LONG;
        }
    }

    private boolean isDate(String value) {
        Matcher matcher = datePattern.matcher(value);
        return matcher.matches();
    }

    private boolean isString(String value) {
        Matcher matcher = stringPattern.matcher(value);
        return matcher.matches();
    }

    private boolean isChar(String value) {
        Matcher matcher = charPattern.matcher(value);
        return matcher.matches();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String value) {
        return value.contains(".");
    }

    private boolean isBoolean(String value) {
        return value.equals("true") | value.equals("false");
    }
}
