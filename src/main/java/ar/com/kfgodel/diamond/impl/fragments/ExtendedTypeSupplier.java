package ar.com.kfgodel.diamond.impl.fragments;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.AnnotatedType;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represent a fragment of code that can extract the extended type from a native class instance
 * Created by kfgodel on 27/09/14.
 */
public class ExtendedTypeSupplier implements Supplier<Optional<TypeInstance>> {
    private Class<?> nativeClass;

    @Override
    public Optional<TypeInstance> get() {
        AnnotatedType annotatedSuperclass = nativeClass.getAnnotatedSuperclass();
        if(annotatedSuperclass == null){
            return Optional.empty();
        }
        return Optional.of(Diamond.types().fromUnspecific(annotatedSuperclass));
    }

    public static ExtendedTypeSupplier create(Class<?> nativeClass) {
        ExtendedTypeSupplier supplier = new ExtendedTypeSupplier();
        supplier.nativeClass = nativeClass;
        return supplier;
    }

}
