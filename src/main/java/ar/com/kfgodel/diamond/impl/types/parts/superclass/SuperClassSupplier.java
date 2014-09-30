package ar.com.kfgodel.diamond.impl.types.parts.superclass;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a fragment of code that can extract the native superclass of a native class
 * Created by kfgodel on 21/09/14.
 */
public class SuperClassSupplier implements Supplier<Optional<TypeInstance>> {
    private Class<?> nativeClass;

    @Override
    public Optional<TypeInstance> get() {
        Class<?> superclass = nativeClass.getSuperclass();
        if(superclass == null){
            return Optional.empty();
        }
        return Optional.of(Diamond.of(superclass));
    }

    public static SuperClassSupplier create(Class<?> nativeClass) {
        SuperClassSupplier superClassSupplier = new SuperClassSupplier();
        superClassSupplier.nativeClass = nativeClass;
        return superClassSupplier;
    }

}
