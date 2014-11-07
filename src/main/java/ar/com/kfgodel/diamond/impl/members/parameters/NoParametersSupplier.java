package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.function.Supplier;

/**
 * This type represents the supplier of a type that has no parameters
 * Created by kfgodel on 01/11/14.
 */
public class NoParametersSupplier implements Supplier<Nary<TypeInstance>> {

    public static final NoParametersSupplier INSTANCE = new NoParametersSupplier();

    @Override
    public Nary<TypeInstance> get() {
        return NaryFromNative.empty();
    }
}
