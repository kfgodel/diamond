package ar.com.kfgodel.diamond.impl.types.parts.methods;

import ar.com.kfgodel.diamond.impl.types.parts.members.InheritedMemberSupplier;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the native method supplier of all inherited methods for a list of classes
 * Created by kfgodel on 05/10/14.
 */
public class NativeMethodsSupplier implements Supplier<Stream<Method>> {

    private InheritedMemberSupplier<Method> nativeMethodSupplier;

    @Override
    public Stream<Method> get() {
        return nativeMethodSupplier.get();
    }

    public static NativeMethodsSupplier create(Set<Class<?>> baseClasses) {
        NativeMethodsSupplier supplier = new NativeMethodsSupplier();
        supplier.nativeMethodSupplier = InheritedMemberSupplier.create(baseClasses, (superClass) -> Arrays.stream(superClass.getDeclaredMethods()));
        return supplier;
    }

}
