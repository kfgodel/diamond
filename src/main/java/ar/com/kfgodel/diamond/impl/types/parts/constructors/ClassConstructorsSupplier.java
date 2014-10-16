package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.natives.NativeConstructorsSupplier;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the supplier for a native class diamond constructors
 * Created by kfgodel on 15/10/14.
 */
public class ClassConstructorsSupplier implements Supplier<Stream<TypeConstructor>> {

    private Class<?> nativeClass;

    @Override
    public Stream<TypeConstructor> get() {
        Supplier<Stream<Constructor<?>>> constructorSupplier = NativeConstructorsSupplier.create(nativeClass);
        return constructorSupplier.get().map((nativeConstructor)-> Diamond.constructors().from(nativeConstructor));
    }

    public static ClassConstructorsSupplier create(Class<?> nativeClass) {
        ClassConstructorsSupplier supplier = new ClassConstructorsSupplier();
        supplier.nativeClass = nativeClass;
        return supplier;
    }

}
