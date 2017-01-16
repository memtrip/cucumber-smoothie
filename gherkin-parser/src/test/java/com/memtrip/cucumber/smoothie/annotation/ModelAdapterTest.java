package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import org.junit.Test;

import javax.lang.model.element.Element;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ModelAdapterTest {

    @Test
    public void getModel() throws Exception {

        // given
        Element element = mock(Element.class);

        ValueAdapter valueAdapter = mock(ValueAdapter.class);
        when(valueAdapter.value(element)).thenReturn("the cow weighs <weight> kg");

        // when
        ModelAdapter<BehaviourModel> modelAdapter = new ModelAdapter<>(valueAdapter);

        // then
        BehaviourModel model = modelAdapter.getModel(element, BehaviourModel.class);

        assertEquals("the cow weighs <weight> kg", model.getValue());
    }
}
