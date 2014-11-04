package ar.com.kfgodel.diamond.impl.types.parts.interfaces;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.streams.TypeStreamSupplierFromNativeTypeArray;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the supplier for interfaces of a fixed type
 * Created by kfgodel on 04/11/14.
 */
public class FixedTypeInterfacesSupplier {

    public static Supplier<Stream<TypeInstance>> create(Class<?> rawClass) {
        return TypeStreamSupplierFromNativeTypeArray.apply(rawClass::getAnnotatedInterfaces);
    }
}
