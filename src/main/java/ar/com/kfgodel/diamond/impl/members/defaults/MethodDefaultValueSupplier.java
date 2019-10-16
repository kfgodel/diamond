package ar.com.kfgodel.diamond.impl.members.defaults;

import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * This type represents the supplier of a native method default value
 * Created by kfgodel on 08/11/14.
 */
public class MethodDefaultValueSupplier implements Supplier<Nary<Object>> {

  private Method nativeMethod;

  public static Supplier<Nary<Object>> create(Method nativeMethod) {
    MethodDefaultValueSupplier supplier = new MethodDefaultValueSupplier();
    supplier.nativeMethod = nativeMethod;
    return supplier;
  }

  @Override
  public Nary<Object> get() {
    Object value = nativeMethod.getDefaultValue();
    if (value == null) {
      return Nary.empty();
    }
    return Nary.of(value);
  }
}
