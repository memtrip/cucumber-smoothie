package com.memtrip.cucumber.smoothie.gherkin;

import java.util.List;

public class FeatureGherkin {
    private String className;
    private List<ScenarioPickle> scenarioPickles;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ScenarioPickle> getScenarioPickles() {
        return scenarioPickles;
    }

    public void setScenarioPickles(List<ScenarioPickle> scenarioPickles) {
        this.scenarioPickles = scenarioPickles;
    }
}
