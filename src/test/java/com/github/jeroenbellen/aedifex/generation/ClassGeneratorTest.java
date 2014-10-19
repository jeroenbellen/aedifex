package com.github.jeroenbellen.aedifex.generation;

import com.github.jeroenbellen.aedifex.generation.dto.ClassProperties;
import com.github.jeroenbellen.aedifex.generation.dto.FieldProperty;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;

public class ClassGeneratorTest {

    private final ClassGenerator classGenerator = ClassGenerator.createInstance();

    @Test
    public void generateClassWithOneField() throws Exception {
        final ArrayList<FieldProperty> fields = new ArrayList<FieldProperty>();
        fields.add(FieldProperty.of("bar", "java.lang.bar"));
        final ClassProperties classProperties = ClassProperties.of("Foo", "bar", fields);
        final String generatedClass = classGenerator.generate(classProperties);
        assertThat(generatedClass)
                .isEqualTo("package bar;\n" +
                        "\n" +
                        "import net.vidageek.mirror.dsl.Mirror;\n" +
                        "import javax.annotation.Generated;\n" +
                        "\n" +
                        "@Generated(\"com.github.jeroenbellen.aedifex.processor.AedifexProcessor\")\n" +
                        "public final class $Foo {\n" +
                        "\n" +
                        "    private final Foo inst;\n" +
                        "\n" +
                        "    private $Foo(){\n" +
                        "        inst = new Mirror().on(Foo.class).invoke().constructor().withoutArgs();\n" +
                        "    }\n" +
                        "\n" +
                        "    public static $Foo with() {\n" +
                        "        return new $Foo();\n" +
                        "    }\n" +
                        "\n" +
                        "    public Foo build() {\n" +
                        "        return inst;\n" +
                        "    }\n" +
                        "\n" +
                        "    public $Foo bar(java.lang.bar bar) {\n" +
                        "        new Mirror().on(inst).set().field(\"bar\").withValue(bar);\n" +
                        "        return this;\n" +
                        "    }\n\n" +
                        "}");

    }

    @Test
    public void generateClassWithTwoFields() throws Exception {
        final ArrayList<FieldProperty> fields = new ArrayList<FieldProperty>();
        fields.add(FieldProperty.of("bar", "java.lang.bar"));
        fields.add(FieldProperty.of("aze", "java.lang.bar"));
        final ClassProperties classProperties = ClassProperties.of("Foo", "bar", fields);
        final String generatedClass = classGenerator.generate(classProperties);
        assertThat(generatedClass)
                .isEqualTo("package bar;\n" +
                        "\n" +
                        "import net.vidageek.mirror.dsl.Mirror;\n" +
                        "import javax.annotation.Generated;\n" +
                        "\n" +
                        "@Generated(\"com.github.jeroenbellen.aedifex.processor.AedifexProcessor\")\n" +
                        "public final class $Foo {\n" +
                        "\n" +
                        "    private final Foo inst;\n" +
                        "\n" +
                        "    private $Foo(){\n" +
                        "        inst = new Mirror().on(Foo.class).invoke().constructor().withoutArgs();\n" +
                        "    }\n" +
                        "\n" +
                        "    public static $Foo with() {\n" +
                        "        return new $Foo();\n" +
                        "    }\n" +
                        "\n" +
                        "    public Foo build() {\n" +
                        "        return inst;\n" +
                        "    }\n" +
                        "\n" +
                        "    public $Foo bar(java.lang.bar bar) {\n" +
                        "        new Mirror().on(inst).set().field(\"bar\").withValue(bar);\n" +
                        "        return this;\n" +
                        "    }\n" +
                        "    public $Foo aze(java.lang.bar aze) {\n" +
                        "        new Mirror().on(inst).set().field(\"aze\").withValue(aze);\n" +
                        "        return this;\n" +
                        "    }\n\n" +
                        "}");

    }

    @Test
    public void generateClassWhenPackageIsNull() throws Exception {
        final ClassProperties classProperties = ClassProperties.of("Foo", null, new ArrayList<FieldProperty>());
        final String generatedClass = classGenerator.generate(classProperties);
        assertThat(generatedClass).doesNotContain("package");
    }

    @Test
    public void generateClassWhenPackageIsEmpty() throws Exception {
        final ClassProperties classProperties = ClassProperties.of("Foo", "", new ArrayList<FieldProperty>());
        final String generatedClass = classGenerator.generate(classProperties);
        assertThat(generatedClass).doesNotContain("package");
    }
}