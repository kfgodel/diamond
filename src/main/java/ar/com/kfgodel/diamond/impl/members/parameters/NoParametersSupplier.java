package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the supplier of a type that has no parameters
 * Created by kfgodel on 01/11/14.
 */
public class NoParametersSupplier implements Supplier<Stream<TypeInstance>> {

    public static final NoParametersSupplier INSTANCE = new NoParametersSupplier();

    @Override
    public Stream<TypeInstance> get() {
        return Stream.empty();
    }
}
