package com.memtrip.cucumber.smoothie.annotation.model.behaviour;

import com.memtrip.cucumber.smoothie.annotation.model.Model;

public class BehaviourModel implements Model {

    public enum Type {
        GIVEN,
        WHEN,
        THEN,
        AND,
        BUT
    }

    private String value;
    private String methodName;
    private Type type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}