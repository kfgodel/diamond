package ar.com.kfgodel.diamond.impl.types.parts.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.natives.NativeMethodsSupplier;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the supplier of class methods from a native class instance.<br>
 *     It supplies instances of classMethods from a given class and its hierarchy
 * Created by kfgodel on 05/10/14.
 */
public class ClassMethodSupplier  {

    public static Supplier<Stream<TypeMethod>> create(Set<Class<?>> baseClasses) {
        return StreamFromCollectionSupplier.using(SuppliedValue.lazilyBy(()->{
            Stream<Method> nativeMethods = NativeMethodsSupplier.create(baseClasses).get();
            return nativeMethods.map((nativeMethod) -> Diamond.methods().from(nativeMethod))
                    .collect(Collectors.toList());
        }));
    }

}
