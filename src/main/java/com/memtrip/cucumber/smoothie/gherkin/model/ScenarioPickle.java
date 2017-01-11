package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.List;

public class ScenarioPickle {
    private String className;
    private List<BehaviourPickle> behaviourPickles;

    public String getClassName() {
        return className;
    }

    public void setClassName(String methodName) {
        this.className = methodName;
    }

    public List<BehaviourPickle> getBehaviourPickles() {
        return behaviourPickles;
    }

    public void setBehaviourPickles(List<BehaviourPickle> behaviourPickles) {
        this.behaviourPickles = behaviourPickles;
    }
}