package ar.com.kfgodel.diamond.impl.types.parts.typeparameters;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a fragment of code that allows you to get the type parameters of a class
 * Created by kfgodel on 25/09/14.
 */
public class TypeParametersSupplier implements Supplier<Stream<TypeInstance>> {
    private Class<?> nativeClass;

    @Override
    public Stream<TypeInstance> get() {
        TypeVariable<? extends Class<?>>[] nativeParameters = nativeClass.getTypeParameters();
        if(nativeParameters.length == 0){
            // Optimization to avoid multiple empty instances
            return Stream.empty();
        }
        Stream<TypeInstance> typeParameters = Arrays.stream(nativeParameters)
                .map((nativeParameter) -> Diamond.types().from(nativeParameter));
        return typeParameters;
    }

    public static TypeParametersSupplier create(Class<?> nativeClass) {
        TypeParametersSupplier supplier = new TypeParametersSupplier();
        supplier.nativeClass = nativeClass;
        return supplier;
    }

}
