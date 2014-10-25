package com.github.jeroenbellen.aedifex.generation.dto;

import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;

public class ClassPropertiesTest {

    @Test
    public void originalClassName() throws Exception {
        final String className = "className";

        final ClassProperties result = ClassProperties.of(className, "bar", new ArrayList<FieldProperty>());

        assertThat(result.getOriginalClassName()).isSameAs(className);
    }

    @Test
    public void className() throws Exception {
        final ClassProperties result = ClassProperties.of("Foo", "bar", new ArrayList<FieldProperty>());

        assertThat(result.getClassName()).isEqualTo("$Foo");
    }

    @Test
    public void fields() throws Exception {
        final ArrayList<FieldProperty> fields = new ArrayList<FieldProperty>();

        final ClassProperties result = ClassProperties.of("Foo", "bar", fields);

        assertThat(result.getFields()).isSameAs(fields);
    }

    @Test
    public void packageName() throws Exception {
        final String packageName = "bar";

        final ClassProperties result = ClassProperties.of("Foo", packageName, new ArrayList<FieldProperty>());

        assertThat(result.getPackageName()).isSameAs(packageName);
    }

    @Test
    public void hasPackagePackageIsGiven() throws Exception {
        final ClassProperties result = ClassProperties.of("Foo", "bar", new ArrayList<FieldProperty>());

        assertThat(result.hasPackage()).isTrue();
    }

    @Test
    public void hasPackagePackageIsNull() throws Exception {
        final ClassProperties result = ClassProperties.of("Foo", null, new ArrayList<FieldProperty>());

        assertThat(result.hasPackage()).isFalse();
    }

    @Test
    public void hasPackagePackageIsEmpty() throws Exception {
        final ClassProperties result = ClassProperties.of("Foo", "", new ArrayList<FieldProperty>());

        assertThat(result.hasPackage()).isFalse();
    }

    @Test
    public void fullyClassifiedClassNameWithPackage() throws Exception {
        final ClassProperties result = ClassProperties.of("Foo", "bar", new ArrayList<FieldProperty>());

        assertThat(result.getFullyClassifiedClassName()).isEqualTo("bar.$Foo");
    }

    @Test
    public void fullyClassifiedClassNameWithPackageNull() throws Exception {
        final ClassProperties result = ClassProperties.of("Foo", null, new ArrayList<FieldProperty>());

        assertThat(result.getFullyClassifiedClassName()).isEqualTo("$Foo");
    }

    @Test
    public void fullyClassifiedClassNameWithPackageEmpty() throws Exception {
        final ClassProperties result = ClassProperties.of("Foo", "", new ArrayList<FieldProperty>());

        assertThat(result.getFullyClassifiedClassName()).isEqualTo("$Foo");
    }
}