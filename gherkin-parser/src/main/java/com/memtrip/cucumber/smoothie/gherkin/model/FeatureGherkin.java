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