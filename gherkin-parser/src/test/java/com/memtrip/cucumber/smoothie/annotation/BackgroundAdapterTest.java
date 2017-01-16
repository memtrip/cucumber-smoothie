package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.annotation.model.BackgroundModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import com.memtrip.cucumber.smoothie.spec.Background;
import org.junit.Test;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class BackgroundAdapterTest {

    @Test
    public void background() {

        // given
        List enclosedElementsList = new ArrayList();

        Element element1 = mock(Element.class);
        when(element1.getEnclosedElements()).thenReturn(enclosedElementsList);

        List<Element> elementList = new ArrayList<>();
        elementList.add(element1);

        BackgroundModel model = new BackgroundModel();

        ModelAdapter adapter = mock(ModelAdapter.class);
        when(adapter.getModel(element1, BackgroundModel.class)).thenReturn(model);

        BehaviourModel givenBehaviour = new BehaviourModel();
        givenBehaviour.setValue("a $100 microwave was sold on 2015-11-03");
        List<BehaviourModel> behaviours = new ArrayList<>();
        behaviours.add(givenBehaviour);

        BehaviourAdapter behaviourAdapter = mock(BehaviourAdapter.class);
        when(behaviourAdapter.behaviours(enclosedElementsList)).thenReturn(behaviours);

        List mirrors = new ArrayList<>();
        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getType(mirrors)).thenReturn(Background.class.getName());

        // when
        BackgroundAdapter backgroundAdapter = new BackgroundAdapter(adapter, behaviourAdapter, typeAdapter);

        // then
        BackgroundModel backgroundModel = backgroundAdapter.background(elementList);
        assertNull(backgroundModel.getValue());

        assertEquals("a $100 microwave was sold on 2015-11-03", backgroundModel.getBehaviours().get(0).getValue());
    }
}