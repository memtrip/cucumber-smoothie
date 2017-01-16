package com.memtrip.cucumber.smoothie.annotation.model.behaviour;

import com.memtrip.cucumber.smoothie.annotation.model.Model;

import java.util.Objects;

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
    private boolean isBackgroundBehaviour;

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

    public boolean isBackgroundBehaviour() {
        return isBackgroundBehaviour;
    }

    public void setBackgroundBehaviour(boolean backgroundBehaviour) {
        isBackgroundBehaviour = backgroundBehaviour;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BehaviourModel) {
            BehaviourModel that = (BehaviourModel) o;

            return Objects.equals(this.value, that.value)
                    && Objects.equals(this.methodName, that.methodName)
                    && Objects.equals(this.type, that.type)
                    && Objects.equals(this.isBackgroundBehaviour, that.isBackgroundBehaviour);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 3 * Objects.hashCode(value)
                + 5 * Objects.hashCode(methodName)
                + 7 * Objects.hashCode(type)
                + 11 * Objects.hashCode(isBackgroundBehaviour);
    }
}