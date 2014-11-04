package ar.com.kfgodel.diamond.impl.types.parts.componenttype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.GenericArrayType;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents the supplier of the component type for a generic array type
 * Created by kfgodel on 29/09/14.
 */
public class ArrayComponentTypeSupplier {

    public static Supplier<Optional<TypeInstance>> create(Object nativeType) {
        return CachedValue.lazilyBy(() -> {
            Object componentType = getComponentTypeFrom(nativeType);
            if(componentType == null){
                return Optional.empty();
            }
            return Optional.of(Diamond.types().from(componentType));
        });
    }

    private static Object getComponentTypeFrom(Object nativeType) {
        Object componentType;
        if(nativeType instanceof AnnotatedArrayType){
            componentType = ((AnnotatedArrayType) nativeType).getAnnotatedGenericComponentType();
        }else if(nativeType instanceof GenericArrayType) {
            componentType = ((GenericArrayType) nativeType).getGenericComponentType();
        }else if(nativeType instanceof Class){
            componentType = ((Class) nativeType).getComponentType();
        }else{
            throw new DiamondException("The type["+nativeType+"] is not a generic array type representation");
        }
        return componentType;
    }

}
