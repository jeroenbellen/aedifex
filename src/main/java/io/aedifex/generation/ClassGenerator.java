package io.aedifex.generation;

public final class ClassGenerator {

    private ClassGenerator() {
    }

    public static String generate(ClassProperties classProperties) {

        final StringBuilder source = new StringBuilder();
        source.append("package ").append(classProperties.getPackageName()).append(";\n\n");
        source.append("public final class ").append(classProperties.getClassName()).append("_ {\n\n");
        source.append("\tprivate ").append(classProperties.getClassName()).append("_(){}\n\n");
        for (FieldProperty field : classProperties.getFields()) {
            source.append(createMethod(classProperties, field));
        }
        source.append("\n}\n\n");

        return source.toString();
    }

    private static String createMethod(ClassProperties classProperties, FieldProperty field) {
        return String.format("\n\tpublic %s %s(%s %s) {" +
                        "\n\t\treturn this;" +
                        "\n\t}",
                classProperties.getClassName() + "_",
                field.getName(),
                field.getType(),
                field.getName()
        );
    }
}
