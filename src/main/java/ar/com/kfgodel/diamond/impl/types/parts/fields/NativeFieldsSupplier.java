package ar.com.kfgodel.diamond.impl.types.parts.fields;

import ar.com.kfgodel.diamond.impl.types.parts.members.InheritedMemberSupplier;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents supplier of native class fields.<br>
 *     It gets all the fields from a type and its hierarchy from the list of native classes
 * Created by kfgodel on 12/10/14.
 */
public class NativeFieldsSupplier implements Supplier<Stream<Field>> {
    private InheritedMemberSupplier<Field> nativeFieldSupplier;

    @Override
    public Stream<Field> get() {
        return nativeFieldSupplier.get();
    }

    public static NativeFieldsSupplier create(Set<Class<?>> rawClasses) {
        NativeFieldsSupplier supplier = new NativeFieldsSupplier();
        supplier.nativeFieldSupplier = InheritedMemberSupplier.create(rawClasses, (superClass) -> Arrays.stream(superClass.getDeclaredFields()));
        return supplier;
    }

}
