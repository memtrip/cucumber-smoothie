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

import com.memtrip.cucumber.smoothie.Log;
import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import com.memtrip.cucumber.smoothie.gherkin.model.ScenarioPickle;
import com.memtrip.cucumber.smoothie.gherkin.model.Tag;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;

import javax.annotation.processing.Filer;
import java.util.ArrayList;
import java.util.List;

public class GherkinAdapter {

    private GherkinParser gherkinParser;

    public GherkinAdapter(Filer filer) {
        gherkinParser = new GherkinParser(new FileUtil(filer), new Compiler(), new Parser<>(new AstBuilder()));
    }

    public List<FeatureGherkin> getFeatureGherkin(List<FeatureModel> featureModels) {

        Log.note("Binding with feature files...");

        List<FeatureGherkin> featureGherkins = new ArrayList<>();

        ArgumentAdapter argumentAdapter = new ArgumentAdapter(new ArgumentTypeMatcher());
        BehaviourPickleAdapter behaviourPickleAdapter = new BehaviourPickleAdapter(argumentAdapter);
        ScenarioPickleAdapter scenarioPickleAdapter = new ScenarioPickleAdapter(behaviourPickleAdapter, new TagAdapter());
        FeatureGherkinAdapter featureGherkinAdapter = new FeatureGherkinAdapter(scenarioPickleAdapter);

        for (FeatureModel featureModel : featureModels) {
            List<Pickle> pickles = gherkinParser.getPickles(
                    featureModel.getProjectRootFolderName(),
                    featureModel.getFeatureFilePath()
            );

            featureGherkins.add(featureGherkinAdapter.getFeatureGherkin(featureModel, pickles));
        }

        Log.note("Bound (" + featureGherkins.size() + ") features.");

        return featureGherkins;
    }

    public List<Tag> getUniqueTags(List<FeatureGherkin> featureGherkins) {
        List<Tag> tags = new ArrayList<>();

        for (FeatureGherkin featureGherkin : featureGherkins) {
            for (ScenarioPickle scenarioPickle : featureGherkin.getScenarioPickles()) {
                List<Tag> scenarioTags = scenarioPickle.getTags();
                for (Tag scenarioTag : scenarioTags) {
                    if (!tags.contains(scenarioTag)) {
                        tags.add(scenarioTag);
                    }
                }
            }
        }

        return tags;
    }
}