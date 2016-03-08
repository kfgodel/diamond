package ar.com.kfgodel.diamond.impl.types.parts.typearguments;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the supplier of a type that has no type arguments
 * Created by kfgodel on 28/09/14.
 */
public class NoTypeArgumentsSupplier implements Supplier<Nary<TypeInstance>> {

    public static final NoTypeArgumentsSupplier INSTANCE = new NoTypeArgumentsSupplier();

    @Override
    public Nary<TypeInstance> get() {
        return Nary.empty();
    }
}
