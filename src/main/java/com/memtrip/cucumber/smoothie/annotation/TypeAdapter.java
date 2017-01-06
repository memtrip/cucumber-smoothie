package com.memtrip.cucumber.smoothie.annotation;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import java.util.List;

class TypeAdapter {

    String getType(List<? extends AnnotationMirror> mirrors) {

        for (AnnotationMirror mirror : mirrors) {
            DeclaredType declaredType = mirror.getAnnotationType();
            String value = declaredType.toString();

            if (value != null) {
                return value;
            }
        }

        return null;
    }

    String getName(Element element) {
        return element.getSimpleName().toString();
    }
}