package io.aedifex.generation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

public final class ClassWriter {

    private ClassWriter() {
    }

    public static void write(ProcessingEnvironment processingEnvironment, ClassProperties classProperties, String source) {
        try {
            JavaFileObject jfo = processingEnvironment.getFiler().createSourceFile(
                    classProperties.getPackageName() + "." + classProperties.getClassName() + "_");
            final Writer writer = jfo.openWriter();
            writer.write(source);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            // TODO do something
        }
    }
}
