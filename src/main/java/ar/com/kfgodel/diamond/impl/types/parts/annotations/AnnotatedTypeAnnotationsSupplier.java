package ar.com.kfgodel.diamond.impl.types.parts.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier of annotations for an annotated type
 * Created by kfgodel on 28/09/14.
 */
public class AnnotatedTypeAnnotationsSupplier implements Supplier<Stream<Annotation>> {

    private AnnotatedType annotatedType;

    @Override
    public Stream<Annotation> get() {
        return Arrays.stream(annotatedType.getAnnotations());
    }

    public static AnnotatedTypeAnnotationsSupplier create(AnnotatedType annotatedType) {
        AnnotatedTypeAnnotationsSupplier supplier = new AnnotatedTypeAnnotationsSupplier();
        supplier.annotatedType = annotatedType;
        return supplier;
    }

}
