package ar.com.kfgodel.diamond.impl.types.parts.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.natives.suppliers.InheritedMemberSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * This type represents the supplier of class methods from a native class instance.<br>
 * It supplies instances of classMethods from a given class and its hierarchy
 * Created by kfgodel on 05/10/14.
 */
public class ClassMethodCalculator implements Supplier<Nary<TypeMethod>> {

  private Supplier<? extends Nary<Class<?>>> runtimeClasses;

  @Override
  public Nary<TypeMethod> get() {
    Nary<Method> nativeMethods = calculateMethodsFor(runtimeClasses);
    return nativeMethods
      .map((nativeMethod) -> Diamond.methods().from(nativeMethod));
  }

  public static ClassMethodCalculator create(Supplier<? extends Nary<Class<?>>> runtimeClasses) {
    ClassMethodCalculator calculator = new ClassMethodCalculator();
    calculator.runtimeClasses = runtimeClasses;
    return calculator;
  }


  private static Nary<Method> calculateMethodsFor(Supplier<? extends Nary<Class<?>>> baseClassesSupplier) {
    final Supplier<Nary<Method>> methodSupplier = InheritedMemberSupplier.create(
      baseClassesSupplier,
      Class::getDeclaredMethods
    );
    return methodSupplier.get();
  }

}
