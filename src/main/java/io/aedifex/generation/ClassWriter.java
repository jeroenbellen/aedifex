package io.aedifex.generation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public final class ClassWriter {

    private ClassWriter() {
    }

    public static void write(ProcessingEnvironment processingEnvironment, ClassProperties classProperties, String source) {
        try {
            JavaFileObject jfo = processingEnvironment.getFiler().createSourceFile(
                    getFullClassName(classProperties));
            final Writer writer = jfo.openWriter();
            writer.write(source);
            writer.close();

        } catch (IOException e) {
            processingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
        }
    }

    private static String getFullClassName(ClassProperties classProperties) {
        final StringBuilder toReturn = new StringBuilder();
        if (classProperties.getPackageName() != null && !classProperties.getPackageName().equals("")) {
            toReturn.append(classProperties.getPackageName()).append(".");
        }
        toReturn.append("$").append(classProperties.getClassName());
        return toReturn.toString();
    }
}
