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

public class ScenarioPickle {
    private String className;
    private String packageName;
    private List<BehaviourPickle> behaviourPickles;
    private List<Tag> tags;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrences) {
        this.occurrence = occurrences;
    }
}