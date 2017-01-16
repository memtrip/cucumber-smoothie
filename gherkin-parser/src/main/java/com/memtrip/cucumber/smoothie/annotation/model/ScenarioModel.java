package com.memtrip.cucumber.smoothie.annotation.model;

import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;

import java.util.List;

public class ScenarioModel implements Model {
    private String value;
    private String className;
    private String packageName;
    private List<BehaviourModel> behaviours;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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
