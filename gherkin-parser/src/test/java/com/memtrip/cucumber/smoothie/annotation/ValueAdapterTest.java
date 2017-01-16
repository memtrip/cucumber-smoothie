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

import javax.lang.model.element.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ValueAdapterTest {

    @Test
    public void getValue() {

        // given
        Name name = mock(Name.class);
        when(name.toString()).thenReturn("value");

        ExecutableElement executableElement = mock(ExecutableElement.class);
        when(executableElement.getSimpleName()).thenReturn(name);

        AnnotationValue annotationValue = mock(AnnotationValue.class);
        when(annotationValue.getValue()).thenReturn("cow.feature");

        Map elementValues = new HashMap();
        elementValues.put(executableElement, annotationValue);

        AnnotationMirror annotationMirror = mock(AnnotationMirror.class);
        when(annotationMirror.getElementValues()).thenReturn(elementValues);

        List mirrors = new ArrayList<>();
        mirrors.add(annotationMirror);

        Element element = mock(Element.class);
        when(element.getAnnotationMirrors()).thenReturn(mirrors);

        // when
        ValueAdapter valueAdapter = new ValueAdapter();

        // then
        assertEquals("cow.feature", valueAdapter.value(element));
    }

    @Test
    public void getKey() {

        // given
        Name name = mock(Name.class);
        when(name.toString()).thenReturn("key");

        ExecutableElement executableElement = mock(ExecutableElement.class);
        when(executableElement.getSimpleName()).thenReturn(name);

        AnnotationValue annotationValue = mock(AnnotationValue.class);
        when(annotationValue.getValue()).thenReturn("cow.feature");

        Map elementValues = new HashMap();
        elementValues.put(executableElement, annotationValue);

        AnnotationMirror annotationMirror = mock(AnnotationMirror.class);
        when(annotationMirror.getElementValues()).thenReturn(elementValues);

        List mirrors = new ArrayList<>();
        mirrors.add(annotationMirror);

        Element element = mock(Element.class);
        when(element.getAnnotationMirrors()).thenReturn(mirrors);

        // when
        ValueAdapter valueAdapter = new ValueAdapter();

        // then
        assertEquals("cow.feature", valueAdapter.value(element, "key"));
    }

    @Test
    public void getEmptyValue() {

        // given
        AnnotationMirror annotationMirror = mock(AnnotationMirror.class);
        when(annotationMirror.getElementValues()).thenReturn(new HashMap());

        List mirrors = new ArrayList<>();
        mirrors.add(annotationMirror);

        Element element = mock(Element.class);
        when(element.getAnnotationMirrors()).thenReturn(mirrors);

        // when
        ValueAdapter valueAdapter = new ValueAdapter();

        // then
        assertNull(valueAdapter.value(element));
    }
}
