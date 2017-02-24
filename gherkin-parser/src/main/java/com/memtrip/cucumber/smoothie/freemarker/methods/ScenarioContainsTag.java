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
package com.memtrip.cucumber.smoothie.freemarker.methods;

import com.memtrip.cucumber.smoothie.gherkin.model.ScenarioPickle;
import com.memtrip.cucumber.smoothie.gherkin.model.Tag;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class ScenarioContainsTag implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        Tag tag = getTag((StringModel) arguments.get(0));
        ScenarioPickle scenarioPickle = getScenarioPickle((StringModel)arguments.get(1));
        return scenarioContainsTag(scenarioPickle, tag);
    }

    private boolean scenarioContainsTag(ScenarioPickle scenarioPickle, Tag tagMatcher) {

        if (scenarioPickle != null) {
            for (Tag tag : scenarioPickle.getTags()) {
                if (tagMatcher.getName().endsWith(tag.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    private Tag getTag(StringModel stringModel) {
        return (Tag) stringModel.getAdaptedObject(Tag.class);
    }

    private ScenarioPickle getScenarioPickle(StringModel stringModel) {
        return (ScenarioPickle) stringModel.getAdaptedObject(ScenarioPickle.class);
    }
}