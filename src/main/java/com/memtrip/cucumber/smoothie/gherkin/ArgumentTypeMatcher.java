package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickleArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentTypeMatcher {
    private Pattern argumentPattern;
    private Pattern datePattern;
    private Pattern stringPattern;
    private Pattern charPattern;

    private static final String DATE_MATCHER = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";
    private static final String STRING_MATCHER = "\"(.*?)\"";
    private static final String CHAR_MATCHER = "\'(.*?)\'";
    private static final String NUMBER_MATCHER = "[+-]?([0-9]*[.])?[0-9]+";
    private static final String BOOLEAN_MATCHER = "(true|false)";

    public ArgumentTypeMatcher() {
        argumentPattern = Pattern.compile(DATE_MATCHER + "|" +
                STRING_MATCHER + "|" +
                CHAR_MATCHER + "|" +
                NUMBER_MATCHER + "|" +
                BOOLEAN_MATCHER);

        datePattern = Pattern.compile(DATE_MATCHER);

        stringPattern = Pattern.compile(STRING_MATCHER);

        charPattern = Pattern.compile(CHAR_MATCHER);
    }

    Matcher matchArgument(String value) {
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
