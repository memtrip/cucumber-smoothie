package com.memtrip.cucumber.adapter;

import com.memtrip.cucumber.model.Model;

import javax.lang.model.element.Element;

import static com.memtrip.cucumber.Constants.VALUE;

public class ModelAdapter<M extends Model> {

    private ValueAdapter valueAdapter;

    public ModelAdapter(ValueAdapter valueAdapter) {
        this.valueAdapter = valueAdapter;
    }

    public M getModel(Element element, Class<? extends M> c) {

        M model = newInstance(c);

        if (model != null) {
            model.setValue(valueAdapter.featureValue(element, VALUE));
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