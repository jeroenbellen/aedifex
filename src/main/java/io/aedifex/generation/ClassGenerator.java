package io.aedifex.generation;

public final class ClassGenerator {

    private ClassGenerator() {
    }

    public static String generate(ClassProperties classProperties) {

        final StringBuilder source = new StringBuilder();
        source.append("package ").append(classProperties.getPackageName()).append(";\n\n");
        source.append("public final class ").append(classProperties.getClassName()).append("_ {\n");
        source.append("\tprivate ").append(classProperties.getClassName()).append("_ {}\n");
        source.append("}\n\n");

        return source.toString();
    }
}
