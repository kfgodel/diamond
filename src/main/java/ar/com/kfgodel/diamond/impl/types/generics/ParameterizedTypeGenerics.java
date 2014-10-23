package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the generic information of a parameterized or parameterizable type
 * Created by kfgodel on 05/10/14.
 */
public class ParameterizedTypeGenerics implements TypeGenerics {

    private Supplier<Stream<TypeInstance>> typeArguments;
    private Supplier<Stream<TypeInstance>> typeParameters;

    public static ParameterizedTypeGenerics create(Supplier<Stream<TypeInstance>> typeParametersSupplier, Supplier<Stream<TypeInstance>> typeArgumentsSupplier) {
        ParameterizedTypeGenerics generics = new ParameterizedTypeGenerics();
        generics.typeParameters = typeParametersSupplier;
        generics.typeArguments = typeArgumentsSupplier;
        return generics;
    }


    @Override
    public TypeBounds bounds() {
        return NotGenerified.INSTANCE.bounds();
    }

    @Override
    public Stream<TypeInstance> typeArguments() {
        return typeArguments.get();
    }

    @Override
    public Stream<TypeInstance> typeParameters() {
        return this.typeParameters.get();
    }
}
