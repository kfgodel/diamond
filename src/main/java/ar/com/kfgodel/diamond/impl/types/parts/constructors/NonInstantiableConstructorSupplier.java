package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier to describe types that have no constructors
 * Created by kfgodel on 15/10/14.
 */
public class NonInstantiableConstructorSupplier implements Supplier<Stream<TypeConstructor>> {

    public static final NonInstantiableConstructorSupplier INSTANCE = new NonInstantiableConstructorSupplier();

    @Override
    public Stream<TypeConstructor> get() {
        return Stream.empty();
    }
}
