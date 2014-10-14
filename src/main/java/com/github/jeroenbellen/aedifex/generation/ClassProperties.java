package com.github.jeroenbellen.aedifex.generation;

import java.util.List;

public class ClassProperties {

    private final String className;
    private final String packageName;
    private final List<FieldProperty> fields;

    private ClassProperties(String className, String packageName, List<FieldProperty> fields) {
        this.className = className;
        this.packageName = packageName;
        this.fields = fields;
    }

    public static ClassProperties of(String className, String packageName, List<FieldProperty> fields) {
        return new ClassProperties(className, packageName, fields);
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<FieldProperty> getFields() {
        return fields;
    }
}
