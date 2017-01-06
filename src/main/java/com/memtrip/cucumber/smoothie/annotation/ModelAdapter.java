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