package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.List;

public class ScenarioPickle {
    private String className;
    private String packageName;
    private List<BehaviourPickle> behaviourPickles;
    private int occurrence;

    public String getClassName() {
        return className;
    }

    public void setClassName(String methodName) {
        this.className = methodName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<BehaviourPickle> getBehaviourPickles() {
        return behaviourPickles;
    }

    public void setBehaviourPickles(List<BehaviourPickle> behaviourPickles) {
        this.behaviourPickles = behaviourPickles;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrences) {
        this.occurrence = occurrences;
    }
}