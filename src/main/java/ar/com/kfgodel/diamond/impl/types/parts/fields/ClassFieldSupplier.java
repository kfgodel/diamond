package ar.com.kfgodel.diamond.impl.types.parts.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.natives.suppliers.NativeFieldsSupplier;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the diamond supplier of type fields from native class instances
 * Created by kfgodel on 12/10/14.
 */
public class ClassFieldSupplier {

    public static Supplier<Stream<TypeField>>  create(Set<Class<?>> baseClasses) {
        return StreamFromCollectionSupplier.lazilyBy(() -> {
            Stream<Field> nativeFields = NativeFieldsSupplier.create(baseClasses).get();
            return nativeFields.map((nativeField) -> Diamond.fields().from(nativeField))
                    .collect(Collectors.toList());
        });
    }

}
