package ar.com.kfgodel.diamond.impl.types.parts.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.natives.suppliers.InheritedMemberSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the supplier of class methods from a native class instance.<br>
 * It supplies instances of classMethods from a given class and its hierarchy
 * Created by kfgodel on 05/10/14.
 */
public class ClassMethodCalculator implements Supplier<List<TypeMethod>> {

  private Supplier<Nary<Class<?>>> runtimeClasses;

  @Override
  public List<TypeMethod> get() {
    Stream<Method> nativeMethods = calculateMethodsFor(runtimeClasses);
    return nativeMethods
      .map((nativeMethod) -> Diamond.methods().from(nativeMethod))
      .collect(Collectors.toList());
  }

  public static ClassMethodCalculator create(Supplier<Nary<Class<?>>> runtimeClasses) {
    ClassMethodCalculator calculator = new ClassMethodCalculator();
    calculator.runtimeClasses = runtimeClasses;
    return calculator;
  }


  private static Stream<Method> calculateMethodsFor(Supplier<Nary<Class<?>>> baseClassesSupplier) {
    final Supplier<Stream<Method>> methodSupplier = InheritedMemberSupplier.create(
      baseClassesSupplier,
      Class::getDeclaredMethods
    );
    return methodSupplier.get();
  }

}
