package com.memtrip.cucumber.model;

public class ScenarioModel implements Model {
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
