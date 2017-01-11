package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.List;

public class BackgroundPickle {
    private String className;
    private List<BehaviourPickle> steps;

    public String getClassName() {
        return className;
    }

    public void setClassName(String methodName) {
        this.className = methodName;
    }

    public List<BehaviourPickle> getSteps() {
        return steps;
    }

    public void setSteps(List<BehaviourPickle> steps) {
        this.steps = steps;
    }
}
