package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the generic information of a parameterized or parameterizable type
 * Created by kfgodel on 05/10/14.
 */
public class ParameterizedTypeGenerics implements TypeGenerics {

    private LazyValue<List<TypeInstance>> typeArguments;
    private LazyValue<List<TypeInstance>> typeParameters;

    public static ParameterizedTypeGenerics create(Supplier<Stream<TypeInstance>> typeParametersSupplier, Supplier<Stream<TypeInstance>> typeArgumentsSupplier) {
        ParameterizedTypeGenerics generics = new ParameterizedTypeGenerics();
        // Here we decide to cache generics as lists (since it seems strange for a type to change parameterization in runtime)
        generics.typeParameters = SuppliedValue.create(() -> typeParametersSupplier.get().collect(Collectors.toList()));
        generics.typeArguments = SuppliedValue.create(() -> typeArgumentsSupplier.get().collect(Collectors.toList()));
        return generics;
    }


    @Override
    public TypeBounds bounds() {
        return NotGenerified.INSTANCE.bounds();
    }

    @Override
    public Stream<TypeInstance> typeArguments() {
        return typeArguments.get().stream();
    }

    @Override
    public Stream<TypeInstance> typeParameters() {
        return this.typeParameters.get().stream();
    }
}
