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

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import com.memtrip.cucumber.smoothie.spec.Background;

import javax.lang.model.element.Element;
import java.util.List;

class BackgroundAdapter {
    private ModelAdapter<BackgroundModel> modelAdapter;
    private BehaviourAdapter behaviourAdapter;
    private TypeAdapter typeAdapter;

    BackgroundAdapter(ModelAdapter<BackgroundModel> modelAdapter, BehaviourAdapter behaviourAdapter, TypeAdapter typeAdapter) {
        this.modelAdapter = modelAdapter;
        this.behaviourAdapter = behaviourAdapter;
        this.typeAdapter = typeAdapter;
    }

    BackgroundModel background(List<? extends Element> elements) {

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String type = typeAdapter.getGherkinType(element.getAnnotationMirrors());
                if (type != null && type.equals(Background.class.getName())) {
                    BackgroundModel backgroundModel = modelAdapter.getModel(element, BackgroundModel.class);
                    backgroundModel.setBehaviours(behaviourAdapter.behaviours(element.getEnclosedElements()));
                    backgroundModel.setClassName(typeAdapter.getName(element));
                    backgroundModel.setPackageName(typeAdapter.getPackage(element));

                    setAsBackgroundBehaviours(backgroundModel.getBehaviours());

                    return backgroundModel;
                }
            }
        }

        return null;
    }

    private void setAsBackgroundBehaviours(List<BehaviourModel> behaviours) {
        for (BehaviourModel behaviour : behaviours) {
            behaviour.setBackgroundBehaviour(true);
        }
    }
}