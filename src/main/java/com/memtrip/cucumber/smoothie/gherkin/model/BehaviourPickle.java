package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.List;

public class BehaviourPickle {
    private String methodName;
    List<BehaviourPickleArgument> arguments;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<BehaviourPickleArgument> getArguments() {
        return arguments;
    }

    public void setArguments(List<BehaviourPickleArgument> arguments) {
        this.arguments = arguments;
    }
}
