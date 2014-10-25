package com.github.jeroenbellen.aedifex.processor;

import com.github.jeroenbellen.aedifex.annotation.AedifexIgnore;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.HashSet;
import java.util.Set;

public class FieldAnalyser {

    private FieldAnalyser() {
    }

    public static FieldAnalyser instance() {
        return new FieldAnalyser();
    }

    public boolean isValidForBuilder(Element element) {
        if (isAnnotatedWithAedifexIgnore(element) || hasNoModifiers(element)) {
            return false;
        }

        final Set<Modifier> modifiers = new HashSet<Modifier>(element.getModifiers());
        removeValidModifiers(modifiers);

        return modifiers.size() == 0;
    }

    private boolean hasNoModifiers(Element element) {
        return element.getModifiers().size() == 0;
    }

    private void removeValidModifiers(Set<Modifier> modifiers) {
        modifiers.remove(Modifier.PUBLIC);
        modifiers.remove(Modifier.PROTECTED);
        modifiers.remove(Modifier.PRIVATE);
    }

    private boolean isAnnotatedWithAedifexIgnore(Element element) {
        return element.getAnnotation(AedifexIgnore.class) != null;
    }
}
