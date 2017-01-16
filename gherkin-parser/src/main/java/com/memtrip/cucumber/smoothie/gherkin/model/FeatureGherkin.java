package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.List;

public class FeatureGherkin {
    private String className;
    private List<ScenarioPickle> scenarioPickles;
    private BackgroundGherkin backgroundGherkin;

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

    public BackgroundGherkin getBackground() {
        return backgroundGherkin;
    }

    public void setBackgroundGherkin(BackgroundGherkin backgroundGherkin) {
        this.backgroundGherkin = backgroundGherkin;
    }

    public boolean hasBackground() {
        return backgroundGherkin != null;
    }
}