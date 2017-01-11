package com.memtrip.cucumber.smoothie.gherkin.model;

public class BehaviourPickleArgument {
    private String key;
    private String value;
    private Type type;

    public enum Type {
        DATE,
        STRING,
        CHAR,
        DOUBLE,
        BOOLEAN,
        INT,
        LONG
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
