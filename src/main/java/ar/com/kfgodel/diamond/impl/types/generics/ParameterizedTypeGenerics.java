package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the generic information of a parameterized or parameterizable type
 * Created by kfgodel on 05/10/14.
 */
public class ParameterizedTypeGenerics implements TypeGenerics {

    private Supplier<Nary<TypeInstance>> typeArguments;
    private Supplier<Nary<TypeInstance>> typeParameters;

    public static ParameterizedTypeGenerics create(Supplier<Nary<TypeInstance>> typeParametersSupplier, Supplier<Nary<TypeInstance>> typeArgumentsSupplier) {
        ParameterizedTypeGenerics generics = new ParameterizedTypeGenerics();
        generics.typeParameters = typeParametersSupplier;
        generics.typeArguments = typeArgumentsSupplier;
        return generics;
    }


    @Override
    public TypeBounds bounds() {
        return UnGenerifiedTypeGenerics.INSTANCE.bounds();
    }

    @Override
    public Nary<TypeInstance> arguments() {
        return typeArguments.get();
    }

    @Override
    public Nary<TypeInstance> parameters() {
        return this.typeParameters.get();
    }
}
