package com.github.jeroenbellen.aedifex;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class AedifexFactoryTest {

    private AedifexFactory aedifexFactory = AedifexFactory.instance();

    @Test
    public void getFieldAnalyser() throws Exception {
        assertThat(aedifexFactory.getFieldAnalyser()).isNotNull();
    }

    @Test
    public void getFieldPropertiesBuilder() throws Exception {
        assertThat(aedifexFactory.getFieldPropertiesBuilder()).isNotNull();
    }

    @Test
    public void getClassPropertiesBuilder() throws Exception {
        assertThat(aedifexFactory.getClassPropertiesBuilder()).isNotNull();
    }

    @Test
    public void getClassGenerator() throws Exception {
        assertThat(aedifexFactory.getClassGenerator()).isNotNull();
    }

    @Test
    public void getClassWriter() throws Exception {
        assertThat(aedifexFactory.getClassWriter()).isNotNull();
    }
}