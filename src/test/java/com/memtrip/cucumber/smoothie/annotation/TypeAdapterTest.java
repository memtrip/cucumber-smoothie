package com.memtrip.cucumber.smoothie.annotation;

import org.junit.Test;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.type.DeclaredType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TypeAdapterTest {

    @Test
    public void getRecognisedType() {

        // given
        DeclaredType declaredType1 = mock(DeclaredType.class);
        AnnotationMirror annotationMirror1 = mock(AnnotationMirror.class);
        when(annotationMirror1.getAnnotationType()).thenReturn(declaredType1);
        when(declaredType1.toString()).thenReturn("Feature");

        List<AnnotationMirror> mirrors = new ArrayList<>();
        mirrors.add(annotationMirror1);

        // when
        TypeAdapter typeAdapter = new TypeAdapter();

        // then
        assertEquals("Feature", typeAdapter.getType(mirrors));
    }

    @Test
    public void getUnrecognisedType() {

        // given
        DeclaredType declaredType1 = mock(DeclaredType.class);
        AnnotationMirror annotationMirror1 = mock(AnnotationMirror.class);
        when(annotationMirror1.getAnnotationType()).thenReturn(declaredType1);
        when(declaredType1.toString()).thenReturn(null);

        List<AnnotationMirror> mirrors = new ArrayList<>();
        mirrors.add(annotationMirror1);

        // when
        TypeAdapter typeAdapter = new TypeAdapter();

        // then
        assertNull(typeAdapter.getType(mirrors));
    }

    @Test
    public void getMethodName() {

        // given
        TypeAdapter typeAdapter = new TypeAdapter();

        // when
        Name name = mock(Name.class);
        when(name.toString()).thenReturn("Jeff_has_bought_a_microwave_for_price");

        Element element = mock(Element.class);
        when(element.getSimpleName()).thenReturn(name);

        // then
        assertEquals("Jeff_has_bought_a_microwave_for_price", typeAdapter.getName(element));
    }
}
