package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.Constants;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.util.List;
import java.util.Map;

class ValueAdapter {

    String value(Element element) {
        return value(element, Constants.VALUE);
    }

    String value(Element element, String key) {
        List<? extends AnnotationMirror> mirrors = element.getAnnotationMirrors();
        for (AnnotationMirror mirror : mirrors) {
            Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = mirror.getElementValues();

            for (ExecutableElement e : elementValues.keySet()) {
                if (key.equals(e.getSimpleName().toString())) {
                    return elementValues.get(e).getValue().toString();
                }
            }
        }

        return null;
    }
}