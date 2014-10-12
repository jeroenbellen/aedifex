package io.aedifex.generation;

import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;

public class ClassGeneratorTest {

    @Test
    public void generateClass() throws Exception {
        final ClassProperties classProperties = ClassProperties.of("Foo", "bar", new ArrayList<FieldProperty>());
        final String generatedClass = ClassGenerator.generate(classProperties);
        assertThat(generatedClass)
                .isEqualTo("package bar;\n" +
                        "\n" +
                        "import net.vidageek.mirror.dsl.Mirror;\n" +
                        "\n" +
                        "public final class Foo_ {\n" +
                        "\n" +
                        "    private final Foo inst;\n" +
                        "\n" +
                        "    private Foo_(){\n" +
                        "        inst = new Mirror().on(Foo.class).invoke().constructor().withoutArgs();\n" +
                        "    }\n" +
                        "\n" +
                        "    public static Foo_ with() {\n" +
                        "        return new Foo_();\n" +
                        "    }\n" +
                        "\n" +
                        "    public Foo build() {\n" +
                        "        return inst;\n" +
                        "    }\n" +
                        "}\n");

    }

}