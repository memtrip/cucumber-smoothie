package com.memtrip.cucumber.adapter;

import com.memtrip.cucumber.model.FeatureModel;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FeatureAdapter {

    private ModelAdapter<FeatureModel> modelAdapter;

    public FeatureAdapter(ModelAdapter<FeatureModel> modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    public List<FeatureModel> features(Set<? extends Element> elements) {

        List<FeatureModel> featureModels = new ArrayList<>();

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                FeatureModel featureModel = modelAdapter.getModel(element, FeatureModel.class);

                List<? extends  Element> elements1 = element.getEnclosedElements();

                //featureModel.setScenarios();
                featureModels.add(featureModel);
            }
        }

        return featureModels;
    }
}
