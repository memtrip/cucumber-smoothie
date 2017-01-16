package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import com.memtrip.cucumber.smoothie.spec.*;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

class BehaviourAdapter {
    private ModelAdapter<BehaviourModel> modelAdapter;
    private TypeAdapter typeAdapter;

    BehaviourAdapter(ModelAdapter<BehaviourModel> modelAdapter, TypeAdapter typeAdapter) {
        this.modelAdapter = modelAdapter;
        this.typeAdapter = typeAdapter;
    }

    List<BehaviourModel> behaviours(List<? extends Element> elements) {

        List<BehaviourModel> behaviours = new ArrayList<>();

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String type = typeAdapter.getType(element.getAnnotationMirrors());
                if (type != null) {
                    BehaviourModel behaviour = modelAdapter.getModel(element, BehaviourModel.class);
                    behaviour.setMethodName(typeAdapter.getName(element));
                    behaviour.setType(getBehaviourType(type));
                    behaviours.add(behaviour);
                }
            }
        }

        return behaviours;
    }

    private BehaviourModel.Type getBehaviourType(String type) {
        if (type != null) {
            if (type.equals(Given.class.getName())) {
                return BehaviourModel.Type.GIVEN;
            } else if (type.equals(When.class.getName())) {
                return BehaviourModel.Type.WHEN;
            } else if (type.equals(Then.class.getName())) {
                return BehaviourModel.Type.THEN;
            } else if (type.equals(And.class.getName())) {
                return BehaviourModel.Type.AND;
            } else if (type.equals(But.class.getName())) {
                return BehaviourModel.Type.BUT;
            }
        }

        return null;
    }
}