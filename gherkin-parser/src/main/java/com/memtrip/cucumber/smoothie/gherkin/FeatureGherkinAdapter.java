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
package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.gherkin.model.BackgroundGherkin;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import gherkin.pickles.Pickle;

import java.util.List;

class FeatureGherkinAdapter {
    private ScenarioPickleAdapter scenarioPickleAdapter;

    FeatureGherkinAdapter(ScenarioPickleAdapter scenarioPickleAdapter) {
        this.scenarioPickleAdapter = scenarioPickleAdapter;
    }

    FeatureGherkin getFeatureGherkin(FeatureModel featureModel, List<Pickle> pickles) {
        FeatureGherkin featureGherkin = new FeatureGherkin();
        featureGherkin.setClassName(featureModel.getClassName());
        featureGherkin.setOneShot(featureModel.isOneShot());
        featureGherkin.setScenarioPickles(scenarioPickleAdapter.getScenarioPickles(
                featureModel.getScenarios(),
                featureModel.getBackground(),
                pickles
        ));

        if (featureModel.getBackground() != null) {
            BackgroundGherkin backgroundGherkin = new BackgroundGherkin();
            backgroundGherkin.setClassName(featureModel.getBackground().getClassName());
            backgroundGherkin.setPackageName(featureModel.getBackground().getPackageName());
            featureGherkin.setBackgroundGherkin(backgroundGherkin);
        }

        return featureGherkin;
    }
}