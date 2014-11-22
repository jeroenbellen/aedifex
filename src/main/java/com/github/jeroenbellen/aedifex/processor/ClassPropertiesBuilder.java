package com.github.jeroenbellen.aedifex.processor;

import com.github.jeroenbellen.aedifex.AedifexFactory;
import com.github.jeroenbellen.aedifex.dto.ClassProperties;
import com.github.jeroenbellen.aedifex.dto.FieldProperty;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.util.List;

public class ClassPropertiesBuilder {

    private final FieldPropertiesBuilder fieldPropertiesBuilder;

    private ClassPropertiesBuilder(FieldPropertiesBuilder fieldPropertiesBuilder) {
        this.fieldPropertiesBuilder = fieldPropertiesBuilder;
    }

    public static ClassPropertiesBuilder instance(AedifexFactory aedifexFactory) {
        return new ClassPropertiesBuilder(aedifexFactory.getFieldPropertiesBuilder());
    }

    public ClassProperties build(TypeElement element) {
        final String className = getClassName(element);
        final String packageName = getPackageName(element);
        final List<FieldProperty> fieldProperties = getFieldProperties(element);
        return ClassProperties.of(
                className,
                packageName,
                fieldProperties);
    }

    private List<FieldProperty> getFieldProperties(TypeElement element) {
        return fieldPropertiesBuilder.build(element.getEnclosedElements());
    }

    private String getClassName(TypeElement element) {
        return element.getSimpleName().toString();
    }

    private String getPackageName(TypeElement classElement) {
        PackageElement packageElement =
                (PackageElement) classElement.getEnclosingElement();

        return packageElement.getQualifiedName().toString();
    }

}
