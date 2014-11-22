package com.github.jeroenbellen.aedifex.generation;

import com.github.jeroenbellen.aedifex.dto.ClassProperties;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class ClassGenerator {

    private static final STGroup ST_GROUP = new STGroupFile("aedifex.stg");

    private ClassGenerator() {
    }

    public static ClassGenerator createInstance() {
        return new ClassGenerator();
    }

    public String generate(ClassProperties classProperties) {
        final ST st = getBuilderClassTemplate();

        st.add("className", classProperties.getClassName());
        st.add("originalClassName", classProperties.getOriginalClassName());
        st.add("packageName", classProperties.getPackageName());
        st.add("hasPackageName", classProperties.hasPackage());
        st.add("fields", classProperties.getFields());

        return st.render();

    }

    private static ST getBuilderClassTemplate() {
        return ST_GROUP.getInstanceOf("builderClass");
    }


}
