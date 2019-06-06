package com.justsafe.android.core.bean.compiler;

import com.justsafe.android.core.bean.annotations.BeanManager;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class ProcessingUtils {

    private ProcessingUtils() {
        // not to be instantiated in public
    }

    public static Set<TypeElement> getTypeElementsToProcess(
            Set<? extends Element> elements, Set<? extends Element> supportedAnnotations) {
        Set<TypeElement> typeElements = new HashSet<>();
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
                    for (Element annotation : supportedAnnotations) {
                        if (mirror.getAnnotationType().asElement().equals(annotation)) {
                            typeElements.add((TypeElement) element);
                            break;
                        }
                    }
                }
            }
        }
        return typeElements;
    }

    public static TypeElement getTypeElementsToProcess(Set<? extends Element> elements) {
        TypeElement typeElements = null;
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
                    Element element2 = mirror.getAnnotationType().asElement();
                    if (element2.toString().equals(BeanManager.class.getName())) {
                        typeElements = (TypeElement) element;
                        break;
                    }
                }
            }
        }
        return typeElements;
    }
}
