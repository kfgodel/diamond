package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.natives.suppliers.NativeConstructorsSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the supplier for a native class diamond constructors
 * Created by kfgodel on 15/10/14.
 */
public class ClassConstructorsSupplier {

  public static Supplier<Nary<TypeConstructor>> create(Class<?> nativeClass) {
    return NarySupplierFromCollection.lazilyBy(() -> {
      Stream<Constructor<?>> constructorSupplier = NativeConstructorsSupplier.create(nativeClass).get();
      return constructorSupplier.map((nativeConstructor) -> Diamond.constructors().from(nativeConstructor))
        .collect(Collectors.toList());
    });
  }

  public static Supplier<Nary<TypeConstructor>> selectFor(Class<?> rawClass) {
    if (rawClass.isArray()) {
      // Artificial constructor for arrays: https://github.com/kfgodel/diamond/issues/88
      return ArraysConstructorSupplier.create(rawClass);
    }
    return ClassConstructorsSupplier.create(rawClass);
  }
}
