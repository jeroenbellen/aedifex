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

}
