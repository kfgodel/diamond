package ar.com.kfgodel.diamond.impl.types.parts.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.ClassField;
import ar.com.kfgodel.diamond.impl.natives.NativeFieldsSupplier;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the diamond supplier of type fields from native class instances
 * Created by kfgodel on 12/10/14.
 */
public class ClassFieldSupplier implements Supplier<Stream<ClassField>> {

    private Set<Class<?>> baseClasses;

    @Override
    public Stream<ClassField> get() {
        Supplier<Stream<Field>> nativeFields = NativeFieldsSupplier.create(baseClasses);
        return nativeFields.get().map((nativeField) -> Diamond.fields().from(nativeField));
    }

    public static ClassFieldSupplier create(Set<Class<?>> rawClasses) {
        ClassFieldSupplier supplier = new ClassFieldSupplier();
        supplier.baseClasses = rawClasses;
        return supplier;
    }

}
