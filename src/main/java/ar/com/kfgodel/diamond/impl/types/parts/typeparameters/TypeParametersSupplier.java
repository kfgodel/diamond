package ar.com.kfgodel.diamond.impl.types.parts.typeparameters;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents a fragment of code that allows you to get the type parameters of a class
 * Created by kfgodel on 25/09/14.
 */
public class TypeParametersSupplier {

    public static Supplier<Stream<TypeInstance>> create(Class<?> nativeClass) {
        return StreamFromCollectionSupplier.using(SuppliedValue.lazilyBy(()->{
            TypeVariable<? extends Class<?>>[] nativeParameters = nativeClass.getTypeParameters();
            return Arrays.stream(nativeParameters)
                    .map((nativeParameter) -> Diamond.types().from(nativeParameter))
                    .collect(Collectors.toList());
        }));
    }

}
