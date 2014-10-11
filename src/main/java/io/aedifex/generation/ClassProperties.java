package io.aedifex.generation;

public class ClassProperties {

    private final String className;
    private final String packageName;

    private ClassProperties(String className, String packageName) {
        this.className = className;
        this.packageName = packageName;
    }

    public static ClassProperties of(String className, String packageName) {
        return new ClassProperties(className, packageName);
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }
}
