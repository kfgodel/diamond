package ar.com.kfgodel.diamond.impl.natives.suppliers;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the native method supplier of all inherited methods for a list of classes
 * Created by kfgodel on 05/10/14.
 */
public class NativeMethodsSupplier {

  public static Supplier<Stream<Method>> create(Set<Class<?>> baseClasses) {
    return InheritedMemberSupplier.create(baseClasses, (superClass) -> Arrays.stream(superClass.getDeclaredMethods()));
  }

}
