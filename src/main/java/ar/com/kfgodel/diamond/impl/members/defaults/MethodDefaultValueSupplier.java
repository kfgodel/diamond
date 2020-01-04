package ar.com.kfgodel.diamond.impl.members.defaults;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * This type represents the supplier of a native method default value
 * Created by kfgodel on 08/11/14.
 */
public class MethodDefaultValueSupplier implements Supplier<Unary<Object>> {

  private Method nativeMethod;

  public static Supplier<Unary<Object>> create(Method nativeMethod) {
    MethodDefaultValueSupplier supplier = new MethodDefaultValueSupplier();
    supplier.nativeMethod = nativeMethod;
    return supplier;
  }

  @Override
  public Unary<Object> get() {
    return Nary.of(nativeMethod.getDefaultValue());
  }
}
