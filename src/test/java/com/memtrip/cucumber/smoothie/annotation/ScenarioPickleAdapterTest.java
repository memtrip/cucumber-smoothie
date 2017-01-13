package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.spec.Scenario;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;
import com.memtrip.cucumber.smoothie.annotation.model.behaviour.BehaviourModel;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ScenarioPickleAdapterTest {

    @Test
    public void scenarios() {

        // given
        List annotationMirrors = new ArrayList();
        List enclosedElements = new ArrayList();
        Element element1 = mock(Element.class);
        when(element1.getAnnotationMirrors()).thenReturn(annotationMirrors);
        when(element1.getEnclosedElements()).thenReturn(enclosedElements);

        List<Element> elements = new ArrayList<>();
        elements.add(element1);

        TypeAdapter typeAdapter = mock(TypeAdapter.class);
        when(typeAdapter.getType(annotationMirrors)).thenReturn(Scenario.class.getName());
        when(typeAdapter.getName(element1)).thenReturn("Buy_last_coffee");

        ScenarioModel scenarioModel = new ScenarioModel();
        scenarioModel.setValue("Buy last coffee");
        ModelAdapter modelAdapter = mock(ModelAdapter.class);
        when(modelAdapter.getModel(element1, ScenarioModel.class)).thenReturn(scenarioModel);

        BehaviourModel givenBehaviour = new BehaviourModel();
        givenBehaviour.setValue("there are 10 coffees left in the machine");
        List<BehaviourModel> behaviours = new ArrayList<>();
        behaviours.add(givenBehaviour);

        BehaviourAdapter behaviourAdapter = mock(BehaviourAdapter.class);
        when(behaviourAdapter.behaviours(enclosedElements)).thenReturn(behaviours);

        // when
        ScenarioAdapter scenarioAdapter = new ScenarioAdapter(modelAdapter, behaviourAdapter, typeAdapter);

        // then
        List<ScenarioModel> scenarios = scenarioAdapter.scenarios(elements);
        assertEquals(1, scenarios.size());
        assertEquals("Buy last coffee", scenarios.get(0).getValue());
        assertEquals("Buy_last_coffee", scenarios.get(0).getClassName());
        Assert.assertEquals("there are 10 coffees left in the machine", scenarios.get(0).getBehaviours().get(0).getValue());
    }
}
