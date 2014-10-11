package io.aedifex.processor;


import io.aedifex.generation.ClassGenerator;
import io.aedifex.generation.ClassProperties;
import io.aedifex.generation.ClassWriter;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
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

        return ClassProperties.of(
                classElement.getSimpleName().toString(), packageElement.getQualifiedName().toString());
    }
}

