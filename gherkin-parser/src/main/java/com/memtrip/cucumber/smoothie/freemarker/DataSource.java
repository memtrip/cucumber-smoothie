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
package com.memtrip.cucumber.smoothie.freemarker;

import com.memtrip.cucumber.smoothie.freemarker.methods.FormatBehaviourPickleArguments;
import com.memtrip.cucumber.smoothie.freemarker.methods.ScenarioContainsTag;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import com.memtrip.cucumber.smoothie.gherkin.model.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DataSource {

    private List<FeatureGherkin> featureGherkins;
    private Tag tag;

    private static final String FEATURES = "features";
    private static final String TAG = "tag";
    private static final String FORMAT_ARGS = "format_args";
    private static final String SCENARIO_CONTAINS_TAG = "scenario_contains_tag";

    DataSource(List<FeatureGherkin> featureGherkins) {
        this.featureGherkins = featureGherkins;
    }

    DataSource(List<FeatureGherkin> featureGherkins, Tag tag) {
        this.featureGherkins = featureGherkins;
        this.tag = tag;
    }

    Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        map.put(FEATURES, featureGherkins);
        map.put(TAG, tag);
        map.put(FORMAT_ARGS, new FormatBehaviourPickleArguments());
        map.put(SCENARIO_CONTAINS_TAG, new ScenarioContainsTag());
        return map;
    }
}