package ar.com.kfgodel.diamond.impl.types.parts.typearguments;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.streams.TypeNarySupplierFromNativeTypeArray;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.ParameterizedType;
import java.util.function.Supplier;

/**
 * This type represents the argument supplier for a parameterized type
 * Created by kfgodel on 29/09/14.
 */
public class ParameterizedTypeArgumentsSupplier {

    public static Supplier<Nary<TypeInstance>>  create(Object nativeType) {
        return TypeNarySupplierFromNativeTypeArray.apply(() -> getActualTypeArgumentsFrom(nativeType));
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
