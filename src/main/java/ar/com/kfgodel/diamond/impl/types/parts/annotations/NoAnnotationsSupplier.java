package ar.com.kfgodel.diamond.impl.types.parts.annotations;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * This type represents the supplier of a non annotated type
 * Created by kfgodel on 28/09/14.
 */
public class NoAnnotationsSupplier implements Supplier<Nary<Annotation>> {

    public static final NoAnnotationsSupplier INSTANCE = new NoAnnotationsSupplier();

    @Override
    public Nary<Annotation> get() {
        return NaryFromNative.empty();
    }
}
