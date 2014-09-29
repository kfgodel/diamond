package ar.com.kfgodel.diamond.impl.types.parts.annotations;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * This type represents the supplier of a non annotated type
 * Created by kfgodel on 28/09/14.
 */
public class NoAnnotationsSupplier implements Supplier<Annotation[]> {

    public static final NoAnnotationsSupplier INSTANCE = new NoAnnotationsSupplier();

    /**
     * Constant used to indicate the lack of annotations
     */
    public static final Annotation[] NO_ANNOTATIONS = new Annotation[0];

    @Override
    public Annotation[] get() {
        return NO_ANNOTATIONS;
    }
}
