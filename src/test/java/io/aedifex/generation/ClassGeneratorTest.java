package io.aedifex.generation;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ClassGeneratorTest {

    @Test
    public void generateClass() throws Exception {
        final ClassProperties classProperties = ClassProperties.of("Foo", "bar");
        final String generatedClass = ClassGenerator.generate(classProperties);
        assertThat(generatedClass)
                .isEqualTo("package bar;\n" +
                        "\n" +
                        "public final class Foo_ {\n" +
                        "\tprivate Foo_ {}\n" +
                        "}\n" +
                        "\n");

    }
}