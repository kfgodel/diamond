package ar.com.kfgodel.diamond.impl.types.parts.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.natives.suppliers.InheritedMemberSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the supplier of class methods from a native class instance.<br>
 * It supplies instances of classMethods from a given class and its hierarchy
 * Created by kfgodel on 05/10/14.
 */
public class ClassMethodSupplier {

  public static Supplier<Nary<TypeMethod>> create(Supplier<Nary<Class<?>>> baseClassesSupplier) {
    return NarySupplierFromCollection.lazilyBy(() -> {
      Stream<Method> nativeMethods = calculateMethodsFor(baseClassesSupplier);
      return nativeMethods
        .map((nativeMethod) -> Diamond.methods().from(nativeMethod))
        .collect(Collectors.toList());
    });
  }

  private static Stream<Method> calculateMethodsFor(Supplier<Nary<Class<?>>> baseClassesSupplier) {
    final Set<Class<?>> rawClasses = baseClassesSupplier.get()
      .collect(Collectors.toSet());
    final Supplier<Stream<Method>> methodSupplier = InheritedMemberSupplier.create(
      rawClasses,
      (superClass) -> Arrays.stream(superClass.getDeclaredMethods())
    );
    return methodSupplier.get();
  }

}
