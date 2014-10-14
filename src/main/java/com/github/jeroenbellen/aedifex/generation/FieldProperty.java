package com.github.jeroenbellen.aedifex.generation;

public class FieldProperty {
    private final String name;
    private final String type;

    private FieldProperty(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static FieldProperty of(String name, String type) {
        return new FieldProperty(name, type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
