package com.github.jeroenbellen.aedifex.processor;

import com.github.jeroenbellen.aedifex.AedifexFactory;
import com.github.jeroenbellen.aedifex.generation.dto.ClassProperties;
import com.github.jeroenbellen.aedifex.generation.dto.FieldProperty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClassProperties.class)
public class ClassPropertiesBuilderTest {

    @Mock
    private ClassProperties expectedClassProperties;

    @Mock
    private List<Element> enclosedElements;

    @Mock
    private TypeElement element;

    @Mock
    private FieldPropertiesBuilder fieldPropertiesBuilder;

    @Mock
    private AedifexFactory aedifexFactory;

    private ClassPropertiesBuilder classPropertiesBuilder;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(ClassProperties.class);
        when(aedifexFactory.getFieldPropertiesBuilder()).thenReturn(fieldPropertiesBuilder);
        classPropertiesBuilder = ClassPropertiesBuilder.instance(aedifexFactory);
    }

    @Test
    public void build() throws Exception {
        mockClassProperties();

        ClassProperties classProperties = classPropertiesBuilder.build(element);

        assertThat(classProperties).isSameAs(expectedClassProperties);

    }

    private void mockClassProperties() {
        final ArrayList<FieldProperty> fieldProperties = new ArrayList<FieldProperty>();
        mockFields(fieldProperties);

        final String expectedClass = "foo";
        mockClass(expectedClass);

        final String expectedPackage = "bar";
        mockPackage(expectedPackage);

        when(ClassProperties.of(same(expectedClass), same(expectedPackage), same(fieldProperties))).thenReturn(expectedClassProperties);
    }

    private void mockFields(ArrayList<FieldProperty> fieldProperties) {
        final List<Element> enclosedElements = new ArrayList<Element>();
        doReturn(enclosedElements).when(element).getEnclosedElements();
        when(fieldPropertiesBuilder.build(same(enclosedElements))).thenReturn(fieldProperties);
    }

    private void mockClass(String expectedClass) {
        final Name name = mock(Name.class);
        when(name.toString()).thenReturn(expectedClass);
        when(element.getSimpleName()).thenReturn(name);
    }

    private void mockPackage(String expectedPackage) {
        final PackageElement enclosingElement = mock(PackageElement.class);
        final Name packageName = mock(Name.class);
        when(packageName.toString()).thenReturn(expectedPackage);
        when(enclosingElement.getQualifiedName()).thenReturn(packageName);
        when(element.getEnclosingElement()).thenReturn(enclosingElement);
    }
}