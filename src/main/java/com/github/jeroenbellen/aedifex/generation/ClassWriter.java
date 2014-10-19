package com.github.jeroenbellen.aedifex.generation;

import com.github.jeroenbellen.aedifex.generation.dto.ClassProperties;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public class ClassWriter {

    private ClassWriter() {
    }

    public static ClassWriter createInstance() {
        return new ClassWriter();
    }

    public void write(ProcessingEnvironment processingEnvironment, ClassProperties classProperties, String source) {
        try {
            JavaFileObject jfo = processingEnvironment.getFiler().createSourceFile(
                    classProperties.getFullyClassifiedClassName());
            final Writer writer = jfo.openWriter();
            writer.write(source);
            writer.close();

        } catch (IOException e) {
            processingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
        }
    }

    private String getFullClassName(ClassProperties classProperties) {
        final StringBuilder toReturn = new StringBuilder();
        if (hasPackage(classProperties)) {
            toReturn.append(classProperties.getPackageName()).append(".");
        }
        toReturn.append("$").append(classProperties.getOriginalClassName());
        return toReturn.toString();
    }

    private boolean hasPackage(ClassProperties classProperties) {
        return classProperties.getPackageName() != null && !classProperties.getPackageName().equals("");
    }
}
