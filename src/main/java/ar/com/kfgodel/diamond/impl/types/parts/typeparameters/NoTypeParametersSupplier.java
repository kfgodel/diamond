package ar.com.kfgodel.diamond.impl.types.parts.typeparameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * This type represents the supplier of a type that has no type parameters
 * Created by kfgodel on 28/09/14.
 */
public class NoTypeParametersSupplier implements Supplier<List<TypeInstance>> {

    public static final NoTypeParametersSupplier INSTANCE = new NoTypeParametersSupplier();

    @Override
    public List<TypeInstance> get() {
        return Collections.emptyList();
    }
}