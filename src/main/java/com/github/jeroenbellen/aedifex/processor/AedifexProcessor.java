package com.github.jeroenbellen.aedifex.processor;


import com.github.jeroenbellen.aedifex.AedifexFactory;
import com.github.jeroenbellen.aedifex.generation.ClassGenerator;
import com.github.jeroenbellen.aedifex.generation.ClassWriter;
import com.github.jeroenbellen.aedifex.generation.dto.ClassProperties;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes({"com.github.jeroenbellen.aedifex.annotation.Aedifex"})
public class AedifexProcessor extends AbstractProcessor {

    private final ClassGenerator classGenerator = ClassGenerator.createInstance();
    private final ClassWriter classWriter = ClassWriter.createInstance();
    private final AedifexFactory aedifexFactory = AedifexFactory.instance();

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        for (TypeElement te : annotations) {
            for (Element e : env.getElementsAnnotatedWith(te)) {
                if (e.getKind() == ElementKind.CLASS) {
                    createClass(e);
                }
            }
        }
        return true;
    }

    public void createClass(Element e) {
        final TypeElement typeElement = (TypeElement) e;

        final ClassProperties classProperties = aedifexFactory.getClassPropertiesBuilder().build(typeElement);

        classWriter.write(
                processingEnv,
                classProperties,
                classGenerator.generate(classProperties));
    }

}

