package io.aedifex.processor;


import io.aedifex.annotation.AedifexIgnore;
import io.aedifex.generation.ClassGenerator;
import io.aedifex.generation.ClassProperties;
import io.aedifex.generation.ClassWriter;
import io.aedifex.generation.FieldProperty;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"io.aedifex.annotation.Aedifex"})
public class AedifexProcessor extends AbstractProcessor {

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
        final ClassProperties classProperties = createClassProperties(e);
        ClassWriter.write(
                processingEnv,
                classProperties,
                ClassGenerator.generate(classProperties));
    }

    private ClassProperties createClassProperties(Element e) {
        TypeElement classElement = (TypeElement) e;
        PackageElement packageElement =
                (PackageElement) classElement.getEnclosingElement();


        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing! " + e.toString());

        List<FieldProperty> fieldProperties = new ArrayList<FieldProperty>();

        for (Element element : e.getEnclosedElements()) {
            if (shouldCreateBuilderMethod(element)) {
                fieldProperties.add(
                        FieldProperty.of(element.toString(), element.asType().toString())
                );
            }
        }

        return ClassProperties.of(
                classElement.getSimpleName().toString(),
                packageElement.getQualifiedName().toString(),
                fieldProperties);
    }

    private boolean shouldCreateBuilderMethod(Element element) {
        return element.getKind().isField()
                && !element.getModifiers().contains(Modifier.STATIC)
                && doesNotHaveIgnoreAnnotation(element);
    }

    private boolean doesNotHaveIgnoreAnnotation(Element element) {
        return element.getAnnotation(AedifexIgnore.class) == null;
    }
}

