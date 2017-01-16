package com.memtrip.cucumber.smoothie.freemarker;

import com.memtrip.cucumber.smoothie.freemarker.methods.FormatBehaviourPickleArguments;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DataSource {

    private List<FeatureGherkin> featureGherkins;

    private static final String FEATURES = "features";
    private static final String FORMAT_ARGS = "format_args";

    DataSource(List<FeatureGherkin> featureGherkins) {
        this.featureGherkins = featureGherkins;
    }

    Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        map.put(FEATURES, featureGherkins);
        map.put(FORMAT_ARGS, new FormatBehaviourPickleArguments());
        return map;
    }
}
