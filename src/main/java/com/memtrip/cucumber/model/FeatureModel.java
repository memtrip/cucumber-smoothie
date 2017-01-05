package com.memtrip.cucumber.model;

import java.util.List;

public class FeatureModel implements Model {
    private String value;
    private List<ScenarioModel> scenarios;

    public List<ScenarioModel> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<ScenarioModel> scenarios) {
        this.scenarios = scenarios;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
