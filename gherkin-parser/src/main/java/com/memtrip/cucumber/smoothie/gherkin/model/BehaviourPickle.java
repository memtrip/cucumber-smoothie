package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.List;

public class BehaviourPickle {
    private String methodName;
    private boolean isBackgroundBehaviour;
    private List<BehaviourPickleArgument> arguments;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public boolean isBackgroundBehaviour() {
        return isBackgroundBehaviour;
    }

    public void setBackgroundBehaviour(boolean backgroundBehaviour) {
        isBackgroundBehaviour = backgroundBehaviour;
    }

    public List<BehaviourPickleArgument> getArguments() {
        return arguments;
    }

    public void setArguments(List<BehaviourPickleArgument> arguments) {
        this.arguments = arguments;
    }
}
