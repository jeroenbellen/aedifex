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
            source.append("\tpublic void ").append(field.getName()).append("(").append(field.getType()).append(" ").append(field.getName()).append(") {}\n");
        }
        source.append("}\n\n");

        return source.toString();
    }
}
