package ar.com.kfgodel.diamond.impl.types.parts.names;

import ar.com.kfgodel.diamond.api.naming.TypeNames;
import ar.com.kfgodel.diamond.impl.naming.ClassNames;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents a name supplier for fixed types
 * Created by kfgodel on 29/09/14.
 */
public class FixedTypeNameSupplier implements Supplier<TypeNames> {

    private Class<?> rawClass;
    private Type type;

    @Override
    public TypeNames get() {
        return ClassNames.create(rawClass, type.getTypeName());
    }

    public static FixedTypeNameSupplier create(Class<?> rawClass, Type nativeType) {
        FixedTypeNameSupplier supplier = new FixedTypeNameSupplier();
        supplier.rawClass = rawClass;
        supplier.type = nativeType;
        return supplier;
    }

}
