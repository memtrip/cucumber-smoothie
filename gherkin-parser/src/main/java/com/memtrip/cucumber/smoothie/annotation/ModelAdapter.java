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

import com.memtrip.cucumber.smoothie.annotation.model.Model;

import javax.lang.model.element.Element;

class ModelAdapter<M extends Model> {
    private ValueAdapter valueAdapter;

    ModelAdapter(ValueAdapter valueAdapter) {
        this.valueAdapter = valueAdapter;
    }

    M getModel(Element element, Class<? extends M> c) {

        M model = newInstance(c);

        if (model != null) {
            model.setValue(valueAdapter.value(element));
        }

        return model;
    }

    private M newInstance(Class<? extends M> c) {
        try {
            return c.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}