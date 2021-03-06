package com.github.jeroenbellen.aedifex;

import com.github.jeroenbellen.aedifex.generation.ClassGenerator;
import com.github.jeroenbellen.aedifex.generation.ClassWriter;
import com.github.jeroenbellen.aedifex.processor.ClassPropertiesBuilder;
import com.github.jeroenbellen.aedifex.processor.FieldAnalyser;
import com.github.jeroenbellen.aedifex.processor.FieldPropertiesBuilder;

public class AedifexFactory {

    private AedifexFactory() {
    }

    public static AedifexFactory instance() {
        return new AedifexFactory();
    }

    private final FieldAnalyser fieldAnalyser = FieldAnalyser.instance();
    private final FieldPropertiesBuilder fieldPropertiesBuilder = FieldPropertiesBuilder.instance(this);
    private final ClassPropertiesBuilder classPropertiesBuilder = ClassPropertiesBuilder.instance(this);
    private final ClassGenerator classGenerator = ClassGenerator.createInstance();
    private final ClassWriter classWriter = ClassWriter.createInstance();

    public FieldAnalyser getFieldAnalyser() {
        return fieldAnalyser;
    }

    public FieldPropertiesBuilder getFieldPropertiesBuilder() {
        return fieldPropertiesBuilder;
    }

    public ClassPropertiesBuilder getClassPropertiesBuilder() {
        return classPropertiesBuilder;
    }

    public ClassGenerator getClassGenerator() {
        return classGenerator;
    }

    public ClassWriter getClassWriter() {
        return classWriter;
    }
}
