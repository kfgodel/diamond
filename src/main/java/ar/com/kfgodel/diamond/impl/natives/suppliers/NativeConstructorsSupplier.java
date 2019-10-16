package ar.com.kfgodel.diamond.impl.natives.suppliers;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the native constructor supplier for a given class
 * Created by kfgodel on 15/10/14.
 */
public class NativeConstructorsSupplier {

  public static Supplier<Stream<Constructor<?>>> create(Class<?> nativeClass) {
    return () -> Arrays.stream(nativeClass.getDeclaredConstructors());
  }

}
