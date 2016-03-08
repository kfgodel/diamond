package ar.com.kfgodel.diamond.impl.types.parts.componenttype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.GenericArrayType;
import java.util.function.Supplier;

/**
 * This type represents the supplier of the component type for a generic array type
 * Created by kfgodel on 29/09/14.
 */
public class ArrayComponentTypeSupplier implements Supplier<Nary<TypeInstance>>  {

    private CachedValue<TypeInstance> componentType;

    public static Supplier<Nary<TypeInstance>> create(Object nativeType) {
        ArrayComponentTypeSupplier supplier = new ArrayComponentTypeSupplier();
        supplier.componentType = CachedValue.lazilyBy(() -> {
            Object componentType = getComponentTypeFrom(nativeType);
            if(componentType == null){
                return null;
            }
            return Diamond.types().from(componentType);
        });
        return supplier;
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

    @Override
    public Nary<TypeInstance> get() {
        TypeInstance component = componentType.get();
        if(component == null){
            return Nary.empty();
        }
        return Nary.of(component);
    }
}
