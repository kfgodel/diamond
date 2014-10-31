package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.natives.suppliers.NativeConstructorsSupplier;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the supplier for a native class diamond constructors
 * Created by kfgodel on 15/10/14.
 */
public class ClassConstructorsSupplier {

    public static Supplier<Stream<TypeConstructor>>  create(Class<?> nativeClass) {
        return StreamFromCollectionSupplier.using(SuppliedValue.lazilyBy(()->{
            Stream<Constructor<?>> constructorSupplier = NativeConstructorsSupplier.create(nativeClass).get();
            return constructorSupplier.map((nativeConstructor)-> Diamond.constructors().from(nativeConstructor))
                    .collect(Collectors.toList());
        }));
    }

}
