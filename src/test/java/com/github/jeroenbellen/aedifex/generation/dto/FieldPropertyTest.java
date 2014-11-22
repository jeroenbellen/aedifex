package com.github.jeroenbellen.aedifex.generation.dto;

import com.github.jeroenbellen.aedifex.dto.FieldProperty;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FieldPropertyTest {

    @Test
    public void correctMapping() throws Exception {
        final String name = "name";
        final String type = "type";

        final FieldProperty result = FieldProperty.of(name, type);

        assertThat(result.getName()).isSameAs(name);
        assertThat(result.getType()).isSameAs(type);

    }
}