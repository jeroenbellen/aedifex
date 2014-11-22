package com.github.jeroenbellen.aedifex.processor;

import com.github.jeroenbellen.aedifex.AedifexFactory;
import com.github.jeroenbellen.aedifex.generation.ClassGenerator;
import com.github.jeroenbellen.aedifex.generation.ClassWriter;
import com.github.jeroenbellen.aedifex.dto.ClassProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AedifexFactory.class)
public class AedifexProcessorTest {

    @Mock
    private AedifexFactory aedifexFactory;

    @Mock
    private RoundEnvironment roundEnvironment;

    @Mock
    private ClassPropertiesBuilder classPropertiesBuilder;

    @Mock
    private ClassGenerator classGenerator;

    @Mock
    private ClassWriter classWriter;

    @Mock
    private TypeElement element;

    @Mock
    private ClassProperties classProperties;

    @Mock
    private ProcessingEnvironment processingEnvironment;

    private AedifexProcessor aedifexProcessor;

    private Set<TypeElement> annotations;
    private String source = "source";

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(AedifexFactory.class);
        when(AedifexFactory.instance()).thenReturn(aedifexFactory);
        when(aedifexFactory.getClassPropertiesBuilder()).thenReturn(classPropertiesBuilder);
        when(aedifexFactory.getClassGenerator()).thenReturn(classGenerator);
        when(aedifexFactory.getClassWriter()).thenReturn(classWriter);

        when(classPropertiesBuilder.build(element)).thenReturn(classProperties);
        when(classGenerator.generate(same(classProperties))).thenReturn(source);

        aedifexProcessor = new AedifexProcessor();
        aedifexProcessor.init(processingEnvironment);

        final TypeElement annotation = mock(TypeElement.class);
        annotations = Collections.unmodifiableSet(new HashSet<TypeElement>(Arrays.asList(annotation)));

        final Set<Element> elements = Collections.unmodifiableSet(new HashSet<Element>(Arrays.asList(element)));
        doReturn(elements).when(roundEnvironment).getElementsAnnotatedWith(annotation);
    }

    @Test
    public void getSupportedSourceVersion() throws Exception {
        final SourceVersion supportedSourceVersion = aedifexProcessor.getSupportedSourceVersion();

        assertThat(supportedSourceVersion).isSameAs(SourceVersion.latestSupported());
    }

    @Test
    public void processNotClassElements() throws Exception {
        for (ElementKind elementKind : ElementKind.values()) {
            if (elementKind != ElementKind.CLASS) {
                when(element.getKind()).thenReturn(elementKind);

                aedifexProcessor.process(annotations, roundEnvironment);

                verify(aedifexFactory, times(0)).getClassPropertiesBuilder();
            }
        }
    }

    @Test
    public void processClassElement() throws Exception {
        when(element.getKind()).thenReturn(ElementKind.CLASS);

        aedifexProcessor.process(annotations, roundEnvironment);

        verify(classWriter).write(processingEnvironment, classProperties, source);
    }
}