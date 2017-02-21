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
package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class FeatureAdapter {
    private ValueAdapter valueAdapter;
    private ScenarioAdapter scenarioAdapter;
    private BackgroundAdapter backgroundAdapter;
    private TypeAdapter typeAdapter;

    FeatureAdapter(ValueAdapter valueAdapter, ScenarioAdapter scenarioAdapter, BackgroundAdapter backgroundAdapter, TypeAdapter typeAdapter) {
        this.valueAdapter = valueAdapter;
        this.scenarioAdapter = scenarioAdapter;
        this.backgroundAdapter = backgroundAdapter;
        this.typeAdapter = typeAdapter;
    }

    List<FeatureModel> features(Set<? extends Element> elements) {

        List<FeatureModel> featureModels = new ArrayList<>();

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                FeatureModel featureModel = new FeatureModel();
                featureModel.setFeatureFilePath(valueAdapter.value(element, "featureFilePath"));
                featureModel.setProjectRootFolderName(valueAdapter.value(element, "projectRootFolderName"));
                featureModel.setOneShot(Boolean.parseBoolean(valueAdapter.value(element, "oneShot")));
                featureModel.setClassName(typeAdapter.getName(element));
                featureModel.setScenarios(scenarioAdapter.scenarios(element.getEnclosedElements()));
                featureModel.setBackground(backgroundAdapter.background(element.getEnclosedElements()));

                featureModels.add(featureModel);
            }
        }

        return featureModels;
    }
}
