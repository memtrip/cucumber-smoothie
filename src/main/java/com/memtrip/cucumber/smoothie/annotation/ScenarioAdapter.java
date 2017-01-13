package com.memtrip.cucumber.smoothie.annotation;

import com.memtrip.cucumber.smoothie.spec.Scenario;
import com.memtrip.cucumber.smoothie.annotation.model.ScenarioModel;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

class ScenarioAdapter {
    private ModelAdapter<ScenarioModel> modelAdapter;
    private BehaviourAdapter behaviourAdapter;
    private TypeAdapter typeAdapter;

    ScenarioAdapter(ModelAdapter<ScenarioModel> modelAdapter, BehaviourAdapter behaviourAdapter, TypeAdapter typeAdapter) {
        this.modelAdapter = modelAdapter;
        this.behaviourAdapter = behaviourAdapter;
        this.typeAdapter = typeAdapter;
    }

    List<ScenarioModel> scenarios(List<? extends Element> elements) {

        List<ScenarioModel> scenarioModels = new ArrayList<>();

        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String type = typeAdapter.getType(element.getAnnotationMirrors());
                if (type != null && type.equals(Scenario.class.getName())) {
                    ScenarioModel scenarioModel = modelAdapter.getModel(element, ScenarioModel.class);
                    scenarioModel.setBehaviours(behaviourAdapter.behaviours(element.getEnclosedElements()));
                    scenarioModel.setClassName(typeAdapter.getName(element));
                    scenarioModel.setPackageName(typeAdapter.getPackage(element));
                    scenarioModels.add(scenarioModel);
                }
            }
        }

        return scenarioModels;
    }
}
