package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.FeatureModel;
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
        when(valueAdapter.value(element)).thenReturn("cow.feature");

        // when
        ModelAdapter<FeatureModel> modelAdapter = new ModelAdapter<>(valueAdapter);

        // then
        FeatureModel model = modelAdapter.getModel(element, FeatureModel.class);

        assertEquals("cow.feature", model.getValue());
    }
}
