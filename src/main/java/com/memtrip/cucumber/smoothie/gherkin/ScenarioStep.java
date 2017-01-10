package com.memtrip.cucumber.smoothie.gherkin;

import java.util.List;

public class ScenarioStep {
    private String methodName;
    List<BehaviourArgument> arguments;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<BehaviourArgument> getArguments() {
        return arguments;
    }

    public void setArguments(List<BehaviourArgument> arguments) {
        this.arguments = arguments;
    }
}
