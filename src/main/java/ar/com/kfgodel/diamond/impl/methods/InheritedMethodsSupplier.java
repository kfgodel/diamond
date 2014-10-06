package ar.com.kfgodel.diamond.impl.methods;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This type represents the native method supplier of all inherited methods for a list of classes
 * Created by kfgodel on 05/10/14.
 */
public class InheritedMethodsSupplier implements Supplier<Stream<Method>> {

    private Set<Class<?>> baseClasses;

    @Override
    public Stream<Method> get() {
        LinkedHashSet<Method> nonDuplicateMethods = baseClasses.stream()
                .flatMap((baseClass) -> StreamSupport.stream(SuperclassSpliterator.create(baseClass), false))
                .flatMap((superClass) -> Arrays.stream(superClass.getDeclaredMethods()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return nonDuplicateMethods.stream();
    }

    public static InheritedMethodsSupplier create(Set<Class<?>> baseClasses) {
        InheritedMethodsSupplier supplier = new InheritedMethodsSupplier();
        supplier.baseClasses = baseClasses;
        return supplier;
    }

}
