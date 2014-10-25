package com.github.jeroenbellen.aedifex.processor;

import com.github.jeroenbellen.aedifex.annotation.AedifexIgnore;
import com.sun.javafx.collections.UnmodifiableListSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FieldAnalyserTest {

    @Mock
    private AedifexIgnore aedifexIgnore;

    @Mock
    private Element element;

    private FieldAnalyser fieldAnalyser = FieldAnalyser.instance();
    private List<Modifier> disallowedModifiers;
    private List<Modifier> allowedModifiers;

    private void modifiers(Modifier... modifier) {
        final Set<Modifier> modifiers = new UnmodifiableListSet<Modifier>(Arrays.asList(modifier));
        when(element.getModifiers()).thenReturn(modifiers);
    }

    private void isValid() {
        assertThat(fieldAnalyser.isValidForBuilder(element)).isTrue();
    }

    private void isInvalid() {
        assertThat(fieldAnalyser.isValidForBuilder(element)).isFalse();
    }

    @Before
    public void setUp() throws Exception {
        allowedModifiers = new ArrayList<Modifier>();
        allowedModifiers.add(Modifier.PRIVATE);
        allowedModifiers.add(Modifier.PUBLIC);
        allowedModifiers.add(Modifier.PROTECTED);

        disallowedModifiers = new ArrayList<Modifier>(Arrays.asList(Modifier.values()));
        disallowedModifiers.remove(Modifier.PRIVATE);
        disallowedModifiers.remove(Modifier.PUBLIC);
        disallowedModifiers.remove(Modifier.PROTECTED);

    }

    @Test
    public void hasAedifexIgnoreAndAllowedModifier() throws Exception {
        when(element.getAnnotation(AedifexIgnore.class)).thenReturn(aedifexIgnore);
        for (Modifier allowedModifier : allowedModifiers) {
            modifiers(allowedModifier);
            isInvalid();
        }
    }

    @Test
    public void hasOnlyAllowedModifiers() throws Exception {
        for (Modifier allowedModifier : allowedModifiers) {
            modifiers(allowedModifier);
            isValid();
        }
    }

    @Test
    public void hasOnlyDisallowedModifier() throws Exception {
        for (Modifier modifier : disallowedModifiers) {
            modifiers(modifier);
            isInvalid();
        }
    }

    @Test
    public void hasAllowedAndDisallowedModifier() throws Exception {
        for (Modifier allowedModifier : allowedModifiers) {
            for (Modifier disallowedModifier : disallowedModifiers) {
                modifiers(allowedModifier, disallowedModifier);
                isInvalid();
            }
        }
    }

    @Test
    public void hasNoModifiers() throws Exception {
        modifiers();
        isInvalid();
    }
}