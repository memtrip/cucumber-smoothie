package com.memtrip.cucumber.smoothie.freemarker.methods;

import com.memtrip.cucumber.smoothie.gherkin.model.BackgroundGherkin;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.ArrayList;
import java.util.List;

public class UniqueFeatureBackgrounds implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return getBackgroundsForFeature(getFeatureGherkins(arguments));
    }

    private List<BackgroundGherkin> getBackgroundsForFeature(List<FeatureGherkin> featureGherkins) {

        List<BackgroundGherkin> backgroundGherkins = new ArrayList<>();

        for (FeatureGherkin featureGherkin : featureGherkins) {
            if (featureGherkin.hasBackground()) {
                if (!backgroundGherkins.contains(featureGherkin.getBackground())) {
                    backgroundGherkins.add(featureGherkin.getBackground());
                }
            }
        }

        return backgroundGherkins;
    }

    private List<FeatureGherkin> getFeatureGherkins(Object simpleSequenceValue) throws TemplateModelException {
        List<FeatureGherkin> args = new ArrayList<>();

        if (simpleSequenceValue instanceof List) {
            List collection = (List) simpleSequenceValue;

            for (Object simpleSequence : collection) {
                SimpleSequence templateModel = (SimpleSequence) simpleSequence;
                args.addAll(getFeatures(templateModel));
            }
        }

        return args;
    }

    private List<FeatureGherkin> getFeatures(Object simpleSequenceValue) throws TemplateModelException {
        List<FeatureGherkin> args = new ArrayList<>();

        if (simpleSequenceValue instanceof SimpleSequence) {
            SimpleSequence simpleSequence = (SimpleSequence) simpleSequenceValue;

            for (int i = 0; i < simpleSequence.size(); i++) {
                StringModel templateModel = (StringModel) simpleSequence.get(i);

                FeatureGherkin table = (FeatureGherkin) templateModel.getAdaptedObject(FeatureGherkin.class);

                args.add(table);
            }
        }

        return args;
    }
}