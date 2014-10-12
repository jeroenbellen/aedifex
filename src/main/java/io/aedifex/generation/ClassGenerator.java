package io.aedifex.generation;

import org.stringtemplate.v4.ST;

public final class ClassGenerator {

    private ClassGenerator() {
    }

    public static String generate(ClassProperties classProperties) {


        final ST st = new ST("package <packageName>;\n" +
                "\n" +
                "import net.vidageek.mirror.dsl.Mirror;\n" +
                "\n" +
                "public final class <className> {\n" +
                "\n" +
                "    private final <originalClassName> inst;\n" +
                "\n" +
                "    private <className>(){\n" +
                "        inst = new Mirror().on(<originalClassName>.class).invoke().constructor().withoutArgs();\n" +
                "    }\n" +
                "\n" +
                "    public static <className> with() {\n" +
                "        return new <className>();\n" +
                "    }\n" +
                "\n" +
                "    public <originalClassName> build() {\n" +
                "        return inst;\n" +
                "    }\n" +
                "<fields:{field|" +
                "\n\n\tpublic <className> <field.name>(<field.type> <field.name>) {\n" +
                "\t\tnew Mirror().on(inst).set().field(\"<field.name>\").withValue(<field.name>);\n" +
                "\t\treturn this;\n" +
                "\t\\}" +
                "}>" +
                "\n" +
                "}\n");

        st.add("className", classProperties.getClassName() + "_");
        st.add("originalClassName", classProperties.getClassName());
        st.add("packageName", classProperties.getPackageName());
        st.add("fields", classProperties.getFields());
        return st.render();

    }


}
