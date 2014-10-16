package ar.com.kfgodel.diamond.impl.types.parts.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.natives.NativeMethodsSupplier;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the supplier of class methods from a native class instance.<br>
 *     It supplies instances of classMethods from a given class and its hierarchy
 * Created by kfgodel on 05/10/14.
 */
public class ClassMethodSupplier implements Supplier<Stream<TypeMethod>> {

    private Set<Class<?>> baseClasses;

    @Override
    public Stream<TypeMethod> get() {
        Supplier<Stream<Method>> nativeMethods = NativeMethodsSupplier.create(baseClasses);
        return nativeMethods.get().map((nativeMethod) -> Diamond.methods().from(nativeMethod));
    }

    public static ClassMethodSupplier create(Set<Class<?>> rawClass) {
        ClassMethodSupplier supplier = new ClassMethodSupplier();
        supplier.baseClasses = rawClass;
        return supplier;
    }

}
