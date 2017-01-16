/**
 * Copyright 2013-present memtrip LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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