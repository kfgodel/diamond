package ar.com.kfgodel.diamond.impl.types.parts.typearguments;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the argument supplier for a parameterized type
 * Created by kfgodel on 29/09/14.
 */
public class ParameterizedTypeArgumentsSupplier implements Supplier<Stream<TypeInstance>> {

    private Object nativeType;

    @Override
    public Stream<TypeInstance> get() {
        Object[] actualTypeArguments;
        if(nativeType instanceof AnnotatedParameterizedType){
            actualTypeArguments = ((AnnotatedParameterizedType) nativeType).getAnnotatedActualTypeArguments();
        }else if(nativeType instanceof ParameterizedType){
            actualTypeArguments = ((ParameterizedType) nativeType).getActualTypeArguments();
        }else{
            throw new DiamondException("The type["+nativeType+"] is not a parameterized type representation");
        }
        Stream<TypeInstance> typeArguments = Arrays.stream(actualTypeArguments)
                .map((annotatedType) -> Diamond.types().from(annotatedType));
        return typeArguments;
    }

    public static ParameterizedTypeArgumentsSupplier create(Object nativeType) {
        ParameterizedTypeArgumentsSupplier supplier = new ParameterizedTypeArgumentsSupplier();
        supplier.nativeType = nativeType;
        return supplier;
    }

}
