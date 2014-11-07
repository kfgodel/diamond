package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.function.Supplier;

/**
 * This type represents a supplier to describe types that have no constructors
 * Created by kfgodel on 15/10/14.
 */
public class NonInstantiableConstructorSupplier implements Supplier<Nary<TypeConstructor>> {

    public static final NonInstantiableConstructorSupplier INSTANCE = new NonInstantiableConstructorSupplier();

    @Override
    public Nary<TypeConstructor> get() {
        return NaryFromNative.empty();
    }
}
