/**
 * Copyright 2013-present memtrip LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.memtrip.cucumber.smoothie.annotation;

import org.junit.Test;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TypeAdapterTest {

    @Test
    public void getGherkinTypeThatIsAnnotated() {
        getGherkinType("com.memtrip.cucumber.smoothie.spec.Feature");
        getGherkinType("com.memtrip.cucumber.smoothie.spec.Background");
        getGherkinType("com.memtrip.cucumber.smoothie.spec.Scenario");
        getGherkinType("com.memtrip.cucumber.smoothie.spec.Given");
        getGherkinType("com.memtrip.cucumber.smoothie.spec.When");
        getGherkinType("com.memtrip.cucumber.smoothie.spec.Then");
        getGherkinType("com.memtrip.cucumber.smoothie.spec.And");
        getGherkinType("com.memtrip.cucumber.smoothie.spec.But");
    }

    private void getGherkinType(String name) {

        // given
        DeclaredType declaredType1 = mock(DeclaredType.class);
        AnnotationMirror annotationMirror1 = mock(AnnotationMirror.class);
        when(annotationMirror1.getAnnotationType()).thenReturn(declaredType1);
        when(declaredType1.toString()).thenReturn(name);

        List<AnnotationMirror> mirrors = new ArrayList<>();
        mirrors.add(annotationMirror1);

        // when
        TypeAdapter typeAdapter = new TypeAdapter();

        // then
        assertEquals(name, typeAdapter.getGherkinType(mirrors));
    }

    @Test
    public void getGherkinTypeThatIsNotAnnotated() {

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
        assertNull(typeAdapter.getGherkinType(mirrors));
    }

    @Test
    public void getGherkinTypeAnnotationThatIsNotSupported() {

        // given
        DeclaredType declaredType1 = mock(DeclaredType.class);
        AnnotationMirror annotationMirror1 = mock(AnnotationMirror.class);
        when(annotationMirror1.getAnnotationType()).thenReturn(declaredType1);
        when(declaredType1.toString()).thenReturn("org.junit.Test");

        List<AnnotationMirror> mirrors = new ArrayList<>();
        mirrors.add(annotationMirror1);

        // when
        TypeAdapter typeAdapter = new TypeAdapter();

        // then
        assertNull(typeAdapter.getGherkinType(mirrors));
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

    @Test
    public void getPackage() {

        // given
        TypeAdapter typeAdapter = new TypeAdapter();

        // when
        TypeMirror typeMirror = mock(TypeMirror.class);
        when(typeMirror.toString()).thenReturn("com.memtrip.cucumber.background");

        Element element = mock(Element.class);
        when(element.asType()).thenReturn(typeMirror);

        // then
        assertEquals("com.memtrip.cucumber.background", typeAdapter.getPackage(element));
    }
}
