package ar.com.kfgodel.diamond.impl.types.parts.typearguments;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the argument supplier for a parameterized type
 * Created by kfgodel on 29/09/14.
 */
public class ParameterizedTypeArgumentsSupplier {

    public static Supplier<Stream<TypeInstance>>  create(Object nativeType) {
        return StreamFromCollectionSupplier.using(SuppliedValue.lazilyBy(()->{
            Object[] actualTypeArguments = getActualTypeArgumentsFrom(nativeType);
            return Arrays.stream(actualTypeArguments)
                    .map((annotatedType) -> Diamond.types().from(annotatedType))
                    .collect(Collectors.toList());
        }));
    }

    private static Object[] getActualTypeArgumentsFrom(Object nativeType) {
        Object[] actualTypeArguments;
        if(nativeType instanceof AnnotatedParameterizedType){
            actualTypeArguments = ((AnnotatedParameterizedType) nativeType).getAnnotatedActualTypeArguments();
        }else if(nativeType instanceof ParameterizedType){
            actualTypeArguments = ((ParameterizedType) nativeType).getActualTypeArguments();
        }else{
            throw new DiamondException("The type["+nativeType+"] is not a parameterized type representation");
        }
        return actualTypeArguments;
    }

}
