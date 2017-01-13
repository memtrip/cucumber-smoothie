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
