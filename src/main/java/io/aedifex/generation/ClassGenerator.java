package io.aedifex.generation;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public final class ClassGenerator {

    private static final STGroup ST_GROUP = new STGroupFile("aedifex.stg");

    private ClassGenerator() {
    }


    public static String generate(ClassProperties classProperties) {
        final ST st = getBuilderClassTemplate();

        st.add("className", "$" + classProperties.getClassName());
        st.add("originalClassName", classProperties.getClassName());
        st.add("packageName", classProperties.getPackageName());
        st.add("hasPackageName", hasPackage(classProperties));
        st.add("fields", classProperties.getFields());

        return st.render();

    }

    private static boolean hasPackage(ClassProperties classProperties) {
        return classProperties.getPackageName() != null && !classProperties.getPackageName().equals("");
    }

    private static ST getBuilderClassTemplate() {
        return ST_GROUP.getInstanceOf("builderClass");
    }


}
