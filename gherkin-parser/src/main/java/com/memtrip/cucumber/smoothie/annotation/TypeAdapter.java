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

import com.memtrip.cucumber.smoothie.spec.*;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import java.util.List;

class TypeAdapter {

    String getGherkinType(List<? extends AnnotationMirror> mirrors) {

        for (AnnotationMirror mirror : mirrors) {
            DeclaredType declaredType = mirror.getAnnotationType();
            String value = declaredType.toString();

            if (isSupportedType(value)) {
                return value;
            }
        }

        return null;
    }

    private boolean isSupportedType(String type) {
        return type != null &&
                (type.equals(Feature.class.getName()) ||
                        type.equals(Background.class.getName())||
                        type.equals(Scenario.class.getName()) ||
                        type.equals(Given.class.getName()) ||
                        type.equals(When.class.getName()) ||
                        type.equals(Then.class.getName()) ||
                        type.equals(And.class.getName()) ||
                        type.equals(But.class.getName()));
    }

    String getName(Element element) {
        return element.getSimpleName().toString();
    }

    String getPackage(Element element) {
        return element.asType().toString();
    }
}