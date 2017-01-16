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
package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.Objects;

public class BehaviourPickleArgument {
    private String key;
    private String value;
    private Type type;

    public enum Type {
        DATE,
        STRING,
        CHAR,
        DOUBLE,
        BOOLEAN,
        INT,
        LONG
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BehaviourPickleArgument) {
            BehaviourPickleArgument that = (BehaviourPickleArgument) o;

            return Objects.equals(this.key, that.key)
                    && Objects.equals(this.value, that.value)
                    && Objects.equals(this.type, that.type);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 3 * Objects.hashCode(key)
                + 5 * Objects.hashCode(value)
                + 7 * Objects.hashCode(type);
    }
}
