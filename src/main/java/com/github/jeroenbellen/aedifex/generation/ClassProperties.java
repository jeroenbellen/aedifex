package com.github.jeroenbellen.aedifex.generation;

import java.util.List;

public class ClassProperties {

    private final String originalClassName;
    private final String className;
    private final String fullyClassifiedClassName;
    private final String packageName;
    private final List<FieldProperty> fields;

    private ClassProperties(String originalClassName, String packageName, List<FieldProperty> fields) {
        this.originalClassName = originalClassName;
        this.packageName = packageName;
        this.fields = fields;
        this.className = "$" + originalClassName;
        if (packageName != null && !"".equals(packageName)) {
            this.fullyClassifiedClassName = packageName + className;
        } else {
            this.fullyClassifiedClassName = className;
        }
    }

    public static ClassProperties of(String className, String packageName, List<FieldProperty> fields) {
        return new ClassProperties(className, packageName, fields);
    }

    public boolean hasPackage() {
        return packageName != null && !"".equals(packageName);
    }

    public String getOriginalClassName() {
        return originalClassName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<FieldProperty> getFields() {
        return fields;
    }

    public String getClassName() {
        return className;
    }

    public String getFullyClassifiedClassName() {
        return fullyClassifiedClassName;
    }
}
