package com.memtrip.cucumber.smoothie.freemarker.methods;

import com.memtrip.cucumber.smoothie.gherkin.model.BehaviourPickleArgument;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.ArrayList;
import java.util.List;

public class FormatBehaviourPickleArguments implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return formatArgs(getBehaviours(arguments.get(0)));
    }

    String formatArgs(List<BehaviourPickleArgument> args) {

        StringBuilder sb = new StringBuilder();

        if (args.size() > 0) {
            for (BehaviourPickleArgument argument : args) {
                sb.append(formatArg(argument.getValue(), argument.getType()));
                sb.append(", ");
            }

            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.toString();
    }

    private String formatArg(String value, BehaviourPickleArgument.Type type) {
        switch (type) {
            case DOUBLE:
            case BOOLEAN:
            case INT:
                return value;
            case LONG:
                return value + "L";
            case DATE:
                return "new SimpleDateFormat(\"YYYY-MM-DD\").parse(\"" + value + "\")";
            case STRING:
                return "\"" + value + "\"";
            case CHAR:
                return "\'" + value + "\'";
            default:
                return value;
        }
    }

    private List<BehaviourPickleArgument> getBehaviours(Object simpleSequenceValue) throws TemplateModelException {
        List<BehaviourPickleArgument> args = new ArrayList<>();

        if (simpleSequenceValue instanceof SimpleSequence) {
            SimpleSequence simpleSequence = (SimpleSequence) simpleSequenceValue;

            for (int i = 0; i < simpleSequence.size(); i++) {
                StringModel templateModel = (StringModel) simpleSequence.get(i);

                BehaviourPickleArgument table =
                        (BehaviourPickleArgument) templateModel.getAdaptedObject(BehaviourPickleArgument.class);

                args.add(table);
            }
        }

        return args;
    }
}
