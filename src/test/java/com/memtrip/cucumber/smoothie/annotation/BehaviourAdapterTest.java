package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.spec.*;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.*;
import org.junit.Test;

import javax.lang.model.element.Element;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class BehaviourAdapterTest {

    @Test
    public void givenBehaviour() {

        // given
        List annotationMirrors = new ArrayList();
        Element element1 = mock(Element.class);
        when(element1.getAnnotationMirrors()).thenReturn(annotationMirrors);

        List<Element> elements = new ArrayList<>();
        elements.add(element1);

        BehaviourModel givenBehaviour = new BehaviourModel();
        givenBehaviour.setValue("there are 10 coffees left in the machine");

        ModelAdapter modelAdapter = mock(ModelAdapter.class);
        when(modelAdapter.getModel(element1, BehaviourModel.class)).thenReturn(givenBehaviour);

        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getType(annotationMirrors)).thenReturn(Given.class.getName());
        when(typeAdapter.getName(element1)).thenReturn("there_are_10_coffees_left_in_the_machine");

        // when
        BehaviourAdapter behaviourAdapter = new BehaviourAdapter(modelAdapter, typeAdapter);

        // then
        List<BehaviourModel> behaviours = behaviourAdapter.behaviours(elements);

        assertEquals(1, behaviours.size());
        assertEquals("there are 10 coffees left in the machine", behaviours.get(0).getValue());
        assertEquals(BehaviourModel.Type.GIVEN, behaviours.get(0).getType());
        assertEquals("there_are_10_coffees_left_in_the_machine", behaviours.get(0).getMethodName());
    }

    @Test
    public void whenBehaviour() {

        // given
        List annotationMirrors = new ArrayList();
        Element element1 = mock(Element.class);
        when(element1.getAnnotationMirrors()).thenReturn(annotationMirrors);

        List<Element> elements = new ArrayList<>();
        elements.add(element1);

        BehaviourModel whenBehaviour = new BehaviourModel();
        whenBehaviour.setValue("I press the coffee button");

        ModelAdapter modelAdapter = mock(ModelAdapter.class);
        when(modelAdapter.getModel(element1, BehaviourModel.class)).thenReturn(whenBehaviour);

        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getType(annotationMirrors)).thenReturn(When.class.getName());
        when(typeAdapter.getName(element1)).thenReturn("I_press_the_coffee_button");

        // when
        BehaviourAdapter behaviourAdapter = new BehaviourAdapter(modelAdapter, typeAdapter);

        // then
        List<BehaviourModel> behaviours = behaviourAdapter.behaviours(elements);

        assertEquals(1, behaviours.size());
        assertEquals("I press the coffee button", behaviours.get(0).getValue());
        assertEquals(BehaviourModel.Type.WHEN, behaviours.get(0).getType());
        assertEquals("I_press_the_coffee_button", behaviours.get(0).getMethodName());
    }

    @Test
    public void thenBehaviour() {

        // given
        List annotationMirrors = new ArrayList();
        Element element1 = mock(Element.class);
        when(element1.getAnnotationMirrors()).thenReturn(annotationMirrors);

        List<Element> elements = new ArrayList<>();
        elements.add(element1);

        BehaviourModel thenBehaviour = new BehaviourModel();
        thenBehaviour.setValue("I should be served a coffee");

        ModelAdapter modelAdapter = mock(ModelAdapter.class);
        when(modelAdapter.getModel(element1, BehaviourModel.class)).thenReturn(thenBehaviour);

        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getType(annotationMirrors)).thenReturn(Then.class.getName());
        when(typeAdapter.getName(element1)).thenReturn("I_should_be_served_a_coffee");

        // when
        BehaviourAdapter behaviourAdapter = new BehaviourAdapter(modelAdapter, typeAdapter);

        // then
        List<BehaviourModel> behaviours = behaviourAdapter.behaviours(elements);

        assertEquals(1, behaviours.size());
        assertEquals("I should be served a coffee", behaviours.get(0).getValue());
        assertEquals(BehaviourModel.Type.THEN, behaviours.get(0).getType());
        assertEquals("I_should_be_served_a_coffee", behaviours.get(0).getMethodName());
    }

    @Test
    public void andBehaviour() {

        // given
        List annotationMirrors = new ArrayList();
        Element element1 = mock(Element.class);
        when(element1.getAnnotationMirrors()).thenReturn(annotationMirrors);

        List<Element> elements = new ArrayList<>();
        elements.add(element1);

        BehaviourModel andBehaviour = new BehaviourModel();
        andBehaviour.setValue("I have deposited 1$");

        ModelAdapter modelAdapter = mock(ModelAdapter.class);
        when(modelAdapter.getModel(element1, BehaviourModel.class)).thenReturn(andBehaviour);

        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getType(annotationMirrors)).thenReturn(And.class.getName());
        when(typeAdapter.getName(element1)).thenReturn("I_have_deposited");

        // when
        BehaviourAdapter behaviourAdapter = new BehaviourAdapter(modelAdapter, typeAdapter);

        // then
        List<BehaviourModel> behaviours = behaviourAdapter.behaviours(elements);

        assertEquals(1, behaviours.size());
        assertEquals("I have deposited 1$", behaviours.get(0).getValue());
        assertEquals(BehaviourModel.Type.AND, behaviours.get(0).getType());
        assertEquals("I_have_deposited", behaviours.get(0).getMethodName());
    }

    @Test
    public void butBehaviour() {

        // given
        List annotationMirrors = new ArrayList();
        Element element1 = mock(Element.class);
        when(element1.getAnnotationMirrors()).thenReturn(annotationMirrors);

        List<Element> elements = new ArrayList<>();
        elements.add(element1);

        BehaviourModel butBehaviour = new BehaviourModel();
        butBehaviour.setValue("Jeff forgot his credit card");

        ModelAdapter modelAdapter = mock(ModelAdapter.class);
        when(modelAdapter.getModel(element1, BehaviourModel.class)).thenReturn(butBehaviour);

        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getType(annotationMirrors)).thenReturn(But.class.getName());
        when(typeAdapter.getName(element1)).thenReturn("Jeff_forgot_his_credit_card");

        // when
        BehaviourAdapter behaviourAdapter = new BehaviourAdapter(modelAdapter, typeAdapter);

        // then
        List<BehaviourModel> behaviours = behaviourAdapter.behaviours(elements);

        assertEquals(1, behaviours.size());
        assertEquals("Jeff forgot his credit card", behaviours.get(0).getValue());
        assertEquals(BehaviourModel.Type.BUT, behaviours.get(0).getType());
        assertEquals("Jeff_forgot_his_credit_card", behaviours.get(0).getMethodName());
    }
}
