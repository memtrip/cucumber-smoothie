package com.memtrip.cucumber.smoothie.gherkin;

import java.util.List;

public class ScenarioPickle {
    private String className;
    private List<ScenarioSteps> steps;

    public String getClassName() {
        return className;
    }

    public void setClassName(String methodName) {
        this.className = methodName;
    }

    public List<ScenarioSteps> getSteps() {
        return steps;
    }

    public void setSteps(List<ScenarioSteps> steps) {
        this.steps = steps;
    }
}