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
package com.memtrip.cucumber.smoothie.freemarker;

import com.memtrip.cucumber.smoothie.freemarker.methods.FormatBehaviourPickleArguments;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class DataSourceTest {

    @Test
    public void dataSourceTypes() {
        DataSource dataSource = new DataSource(new ArrayList<FeatureGherkin>());
        assertTrue(dataSource.map().get("features") instanceof List);
        assertTrue(dataSource.map().get("format_args") instanceof FormatBehaviourPickleArguments);
    }
}
