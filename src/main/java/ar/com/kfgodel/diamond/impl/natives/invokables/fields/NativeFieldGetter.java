package ar.com.kfgodel.diamond.impl.natives.invokables.fields;

import ar.com.kfgodel.diamond.impl.natives.NativeMemberAccessibility;
import ar.com.kfgodel.diamond.impl.natives.fragments.NativeFieldGetterFragment;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * This type represents the getter function of a native field
 * Created by kfgodel on 14/11/14.
 */
public class NativeFieldGetter implements Function<Object, Object> {

  private Field nativeField;

  @Override
  public Object apply(Object instance) {
    return NativeFieldGetterFragment.apply(nativeField, instance);
  }

  public static NativeFieldGetter create(Field nativeField) {
    NativeMemberAccessibility.ensuredFor(nativeField);
    NativeFieldGetter getter = new NativeFieldGetter();
    getter.nativeField = nativeField;
    return getter;
  }

}
