package com.github.jeroenbellen.aedifex.processor;

import com.github.jeroenbellen.aedifex.AedifexFactory;
import com.github.jeroenbellen.aedifex.dto.FieldProperty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FieldPropertiesBuilderTest {

    @Mock
    private Element element;

    @Mock
    private AedifexFactory aedifexFactory;

    @Mock
    private FieldAnalyser fieldAnalyser;

    private FieldPropertiesBuilder fieldPropertiesBuilder;

    @Before
    public void setUp() throws Exception {
        when(aedifexFactory.getFieldAnalyser()).thenReturn(fieldAnalyser);
        fieldPropertiesBuilder = FieldPropertiesBuilder.instance(aedifexFactory);
    }

    private List<FieldProperty> build() {
        return fieldPropertiesBuilder.build(Arrays.asList(element));
    }

    @Test
    public void elementIsNotAField() throws Exception {
        for (ElementKind elementKind : ElementKind.values()) {
            if (!elementKind.isField()) {
                when(element.getKind()).thenReturn(elementKind);

                final List<FieldProperty> fieldProperties = build();

                assertThat(fieldProperties).isEmpty();
                verify(fieldAnalyser, times(0)).isValidForBuilder(element);
            }
        }
    }

    @Test
    public void elementIsNotAValidField() throws Exception {
        when(element.getKind()).thenReturn(ElementKind.FIELD);
        when(fieldAnalyser.isValidForBuilder(element)).thenReturn(false);

        final List<FieldProperty> fieldProperties = build();

        assertThat(fieldProperties).isEmpty();
    }

    @Test
    public void elementIsValid() throws Exception {
        when(element.getKind()).thenReturn(ElementKind.FIELD);
        when(fieldAnalyser.isValidForBuilder(element)).thenReturn(true);
        when(element.toString()).thenReturn("foo");
        final TypeMirror typeMirror = mock(TypeMirror.class);
        when(element.asType()).thenReturn(typeMirror);
        when(typeMirror.toString()).thenReturn("bar");

        final List<FieldProperty> fieldProperties = build();

        assertThat(fieldProperties).usingElementComparator(new Comparator<FieldProperty>() {
            @Override
            public int compare(FieldProperty o1, FieldProperty o2) {
                return (o1.getName() + "_" + o1.getType()).compareTo((o2.getName() + "_" + o2.getType()));
            }
        }).containsExactly(FieldProperty.of("foo", "bar"));


    }
}