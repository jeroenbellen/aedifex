package com.github.jeroenbellen.aedifex.processor;

import com.github.jeroenbellen.aedifex.AedifexFactory;
import com.github.jeroenbellen.aedifex.generation.dto.FieldProperty;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

public class FieldPropertiesBuilder {

    private final FieldAnalyser fieldAnalyser;

    private FieldPropertiesBuilder(FieldAnalyser fieldAnalyser) {
        this.fieldAnalyser = fieldAnalyser;
    }

    public static FieldPropertiesBuilder instance(AedifexFactory aedifexFactory) {
        return new FieldPropertiesBuilder(aedifexFactory.getFieldAnalyser());
    }

    public List<FieldProperty> build(List<? extends Element> possibleFieldElements) {
        List<FieldProperty> fieldProperties = new ArrayList<FieldProperty>();

        for (Element element : possibleFieldElements) {
            if (shouldCreateBuilderMethod(element)) {
                fieldProperties.add(
                        FieldProperty.of(element.toString(), element.asType().toString())
                );
            }
        }

        return fieldProperties;
    }

    private boolean shouldCreateBuilderMethod(Element element) {
        return element.getKind().isField()
                && fieldAnalyser.isValidForBuilder(element);
    }

}
