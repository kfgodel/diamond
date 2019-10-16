package ar.com.kfgodel.diamond.impl.natives.invokables.fields;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * This type represents an invoker for static fields
 * Created by kfgodel on 30/10/14.
 */
public class NativeStaticFieldInvoker extends NativeFieldInvokerSupport {

  @Override
  public Object invoke(Object... arguments) {
    if (arguments.length == 0) {
      return get();
    } else if (arguments.length == 1) {
      accept(arguments[0]);
    } else {
      throw new DiamondException("This field[" + this.getNativeField() + "] only accepts 0 or 1 argument: " + Arrays.toString(arguments));
    }
    return null;
  }

  @Override
  public Object get() {
    // Called as a supplier to get the value of this field
    return getValueFrom(null);
  }

  @Override
  public Object apply(Object argument) {
    return getValueFrom(argument);
  }

  @Override
  public void accept(Object newValue) {
    // Called as a consumer to set the value of this field
    setValueOn(null, newValue);
  }

  @Override
  public void accept(Object instance, Object newValue) {
    setValueOn(instance, newValue);
  }

  public static NativeStaticFieldInvoker create(Field nativeField) {
    NativeStaticFieldInvoker invoker = new NativeStaticFieldInvoker();
    invoker.setNativeField(nativeField);
    return invoker;
  }

}
