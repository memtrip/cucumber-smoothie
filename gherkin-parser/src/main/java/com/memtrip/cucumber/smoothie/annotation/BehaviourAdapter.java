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

import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import com.memtrip.cucumber.smoothie.spec.*;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

class BehaviourAdapter {
    private ModelAdapter<BehaviourModel> modelAdapter;
    private TypeAdapter typeAdapter;

    BehaviourAdapter(ModelAdapter<BehaviourModel> modelAdapter, TypeAdapter typeAdapter) {
        this.modelAdapter = modelAdapter;
        this.typeAdapter = typeAdapter;
    }

    List<BehaviourModel> behaviours(List<? extends Element> elements) {

        List<BehaviourModel> behaviours = new ArrayList<>();

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String type = typeAdapter.getGherkinType(element.getAnnotationMirrors());
                if (isGherkinBehaviourType(type)) {
                    BehaviourModel behaviour = modelAdapter.getModel(element, BehaviourModel.class);
                    behaviour.setMethodName(typeAdapter.getName(element));
                    behaviour.setType(getBehaviourType(type));
                    behaviours.add(behaviour);
                }
            }
        }

        return behaviours;
    }

    private boolean isGherkinBehaviourType(String type) {
        return type != null &&
                (type.equals(Given.class.getName()) ||
                        type.equals(When.class.getName()) ||
                        type.equals(Then.class.getName()) ||
                        type.equals(And.class.getName()) ||
                        type.equals(But.class.getName()));
    }

    private BehaviourModel.Type getBehaviourType(String type) {
        if (type != null) {
            if (type.equals(Given.class.getName())) {
                return BehaviourModel.Type.GIVEN;
            } else if (type.equals(When.class.getName())) {
                return BehaviourModel.Type.WHEN;
            } else if (type.equals(Then.class.getName())) {
                return BehaviourModel.Type.THEN;
            } else if (type.equals(And.class.getName())) {
                return BehaviourModel.Type.AND;
            } else if (type.equals(But.class.getName())) {
                return BehaviourModel.Type.BUT;
            }
        }

        return null;
    }
}