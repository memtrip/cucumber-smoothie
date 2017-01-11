package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.spec.Background;
import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;

import javax.lang.model.element.Element;
import java.util.List;

class BackgroundAdapter {
    private ModelAdapter<BackgroundModel> modelAdapter;
    private BehaviourAdapter behaviourAdapter;
    private TypeAdapter typeAdapter;

    BackgroundAdapter(ModelAdapter<BackgroundModel> modelAdapter, BehaviourAdapter behaviourAdapter, TypeAdapter typeAdapter) {
        this.modelAdapter = modelAdapter;
        this.behaviourAdapter = behaviourAdapter;
        this.typeAdapter = typeAdapter;
    }

    BackgroundModel background(List<? extends Element> elements) {

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String type = typeAdapter.getType(element.getAnnotationMirrors());
                if (type != null && type.equals(Background.class.getName())) {
                    BackgroundModel backgroundModel = modelAdapter.getModel(element, BackgroundModel.class);
                    backgroundModel.setClassName(typeAdapter.getName(element));
                    backgroundModel.setBehaviours(behaviourAdapter.behaviours(element.getEnclosedElements()));
                    return backgroundModel;
                }
            }
        }

        return null;
    }
}
