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
package com.memtrip.cucumber.smoothie.annotation.model.behaviour;

import com.memtrip.cucumber.smoothie.annotation.model.Model;

import java.util.Objects;

public class BehaviourModel implements Model {

    public enum Type {
        GIVEN,
        WHEN,
        THEN,
        AND,
        BUT
    }

    private String value;
    private String methodName;
    private Type type;
    private boolean isBackgroundBehaviour;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isBackgroundBehaviour() {
        return isBackgroundBehaviour;
    }

    public void setBackgroundBehaviour(boolean backgroundBehaviour) {
        isBackgroundBehaviour = backgroundBehaviour;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BehaviourModel) {
            BehaviourModel that = (BehaviourModel) o;

            return Objects.equals(this.value, that.value)
                    && Objects.equals(this.methodName, that.methodName)
                    && Objects.equals(this.type, that.type)
                    && Objects.equals(this.isBackgroundBehaviour, that.isBackgroundBehaviour);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 3 * Objects.hashCode(value)
                + 5 * Objects.hashCode(methodName)
                + 7 * Objects.hashCode(type)
                + 11 * Objects.hashCode(isBackgroundBehaviour);
    }
}