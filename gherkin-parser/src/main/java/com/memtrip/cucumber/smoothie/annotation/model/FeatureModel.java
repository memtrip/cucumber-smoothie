package com.memtrip.cucumber.smoothie.annotation.model;

import java.util.List;

public class FeatureModel implements Model {
    private String featureFilePath;
    private String projectRootFolderName;
    private String className;
    private List<ScenarioModel> scenarios;
    private BackgroundModel background;

    public String getFeatureFilePath() {
        return featureFilePath;
    }

    public void setFeatureFilePath(String featureFilePath) {
        this.featureFilePath = featureFilePath;
    }

    public String getProjectRootFolderName() {
        return projectRootFolderName;
    }

    public void setProjectRootFolderName(String projectRootName) {
        this.projectRootFolderName = projectRootName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ScenarioModel> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<ScenarioModel> scenarios) {
        this.scenarios = scenarios;
    }

    public void setBackground(BackgroundModel background) {
        this.background = background;
    }

    public BackgroundModel getBackground() {
        return background;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public void setValue(String value) { }
}