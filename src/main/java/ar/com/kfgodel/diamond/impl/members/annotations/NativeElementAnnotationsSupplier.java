package ar.com.kfgodel.diamond.impl.members.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the annotation supplier for a native annotated element
 * Created by kfgodel on 01/11/14.
 */
public class NativeElementAnnotationsSupplier implements Supplier<Stream<Annotation>> {

    private AnnotatedElement element;


    @Override
    public Stream<Annotation> get() {
        return Arrays.stream(element.getAnnotations());
    }

    public static NativeElementAnnotationsSupplier create(AnnotatedElement element) {
        NativeElementAnnotationsSupplier supplier = new NativeElementAnnotationsSupplier();
        supplier.element = element;
        return supplier;
    }

}
