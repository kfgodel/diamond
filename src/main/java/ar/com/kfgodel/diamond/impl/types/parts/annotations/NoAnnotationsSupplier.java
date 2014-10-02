package ar.com.kfgodel.diamond.impl.types.parts.annotations;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the supplier of a non annotated type
 * Created by kfgodel on 28/09/14.
 */
public class NoAnnotationsSupplier implements Supplier<Stream<Annotation>> {

    public static final NoAnnotationsSupplier INSTANCE = new NoAnnotationsSupplier();

    @Override
    public Stream<Annotation> get() {
        return Stream.empty();
    }
}
