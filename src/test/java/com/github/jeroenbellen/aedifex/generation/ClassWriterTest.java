package com.github.jeroenbellen.aedifex.generation;

import com.github.jeroenbellen.aedifex.generation.dto.ClassProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClassWriterTest {

    private String source = "source";

    @Mock
    private ClassProperties classProperties;
    @Mock
    private ProcessingEnvironment processingEnvironment;
    @Mock
    private Filer filter;
    @Mock
    private JavaFileObject jfo;
    @Mock
    private Writer writer;
    @Mock
    private Messager messager;

    private ClassWriter classWriter = ClassWriter.createInstance();


    @Before
    public void setUp() throws Exception {
        final String fullyClassifiedClassName = "fullyClassifiedClassName";
        when(classProperties.getFullyClassifiedClassName()).thenReturn(fullyClassifiedClassName);
        when(processingEnvironment.getFiler()).thenReturn(filter);
        when(processingEnvironment.getFiler().createSourceFile(same(fullyClassifiedClassName))).thenReturn(jfo);
        when(jfo.openWriter()).thenReturn(writer);

        when(processingEnvironment.getMessager()).thenReturn(messager);
    }

    private void writeSourceWithPackage() {
        classWriter.write(processingEnvironment, classProperties, source);
    }

    private IOException createIoException() {
        return new IOException("an exception");
    }

    private void verifyErrorHandling(IOException ioException) {
        verify(messager).printMessage(same(Diagnostic.Kind.ERROR), same(ioException.getMessage()));
    }

    @Test
    public void generateSourceWithPackage() throws Exception {
        writeSourceWithPackage();

        verify(writer).write(same(source));
        verify(writer).close();
    }

    @Test
    public void createSourceFileThrowsIoException() throws Exception {
        final IOException ioException = createIoException();
        when(processingEnvironment.getFiler().createSourceFile(anyString())).thenThrow(ioException);

        writeSourceWithPackage();

        verifyErrorHandling(ioException);
    }

    @Test
    public void writeThrowsIoException() throws Exception {
        final IOException ioException = createIoException();
        doThrow(ioException).when(writer).write(anyString());

        writeSourceWithPackage();

        verifyErrorHandling(ioException);
    }

    @Test
    public void closeThrowsIoException() throws Exception {
        final IOException ioException = createIoException();
        doThrow(ioException).when(writer).close();

        writeSourceWithPackage();

        verifyErrorHandling(ioException);
    }
}