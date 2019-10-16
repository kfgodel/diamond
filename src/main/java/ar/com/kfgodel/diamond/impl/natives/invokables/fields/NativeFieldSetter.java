package ar.com.kfgodel.diamond.impl.natives.invokables.fields;

import ar.com.kfgodel.diamond.impl.natives.NativeMemberAccessibility;
import ar.com.kfgodel.diamond.impl.natives.fragments.NativeFieldSetterFragment;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;

/**
 * This type represents the setter function for a native field
 * Created by kfgodel on 14/11/14.
 */
public class NativeFieldSetter implements BiConsumer<Object, Object> {

  private Field nativeField;

  @Override
  public void accept(Object instance, Object value) {
    NativeFieldSetterFragment.accept(nativeField, instance, value);
  }

  public static NativeFieldSetter create(Field nativeField) {
    NativeMemberAccessibility.ensuredFor(nativeField);
    NativeFieldSetter setter = new NativeFieldSetter();
    setter.nativeField = nativeField;
    return setter;
  }

}
