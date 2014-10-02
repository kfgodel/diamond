package ar.com.kfgodel.diamond.impl.types.parts.typearguments;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the supplier of a type that has no type arguments
 * Created by kfgodel on 28/09/14.
 */
public class NoTypeArgumentsSupplier implements Supplier<Stream<TypeInstance>> {

    public static final NoTypeArgumentsSupplier INSTANCE = new NoTypeArgumentsSupplier();

    @Override
    public Stream<TypeInstance> get() {
        return Stream.empty();
    }
}
