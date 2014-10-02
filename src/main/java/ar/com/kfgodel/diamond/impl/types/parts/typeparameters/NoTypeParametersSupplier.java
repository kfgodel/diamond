package ar.com.kfgodel.diamond.impl.types.parts.typeparameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the supplier of a type that has no type parameters
 * Created by kfgodel on 28/09/14.
 */
public class NoTypeParametersSupplier implements Supplier<Stream<TypeInstance>> {

    public static final NoTypeParametersSupplier INSTANCE = new NoTypeParametersSupplier();

    @Override
    public Stream<TypeInstance> get() {
        return Stream.empty();
    }
}
