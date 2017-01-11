package com.memtrip.cucumber.smoothie.annotation.model;

import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;

import java.util.List;

public class BackgroundModel implements Model {
    private String value;
    private String className;
    private List<BehaviourModel> behaviours;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<BehaviourModel> getBehaviours() {
        return behaviours;
    }

    public void setBehaviours(List<BehaviourModel> behaviours) {
        this.behaviours = behaviours;
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