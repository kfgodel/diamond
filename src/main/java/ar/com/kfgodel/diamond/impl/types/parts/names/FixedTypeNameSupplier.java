package ar.com.kfgodel.diamond.impl.types.parts.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.impl.types.names.ClassTypeNames;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents a name supplier for fixed types
 * Created by kfgodel on 29/09/14.
 */
public class FixedTypeNameSupplier {

    public static Supplier<TypeNames> create(Class<?> rawClass, Type nativeType) {
        return CachedValue.lazilyBy(() -> ClassTypeNames.create(rawClass, nativeType.getTypeName()));
    }

}
