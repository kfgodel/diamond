package ar.com.kfgodel.diamond.impl.natives.invokables.fields;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.impl.natives.NativeMemberAccessibility;
import ar.com.kfgodel.diamond.impl.natives.fragments.NativeFieldGetterFragment;
import ar.com.kfgodel.diamond.impl.natives.fragments.NativeFieldSetterFragment;

import java.lang.reflect.Field;

/**
 * This type serves as base class for native field invokers
 * Created by kfgodel on 30/10/14.
 */
public abstract class NativeFieldInvokerSupport implements PolymorphicInvokable {

  private Field nativeField;


  protected void setValueOn(Object instance, Object value) throws DiamondException {
    NativeFieldSetterFragment.accept(nativeField, instance, value);
  }

  protected Object getValueFrom(Object instance) throws DiamondException {
    return NativeFieldGetterFragment.apply(nativeField, instance);
  }


  protected Field getNativeField() {
    return nativeField;
  }

  protected void setNativeField(Field nativeField) {
    NativeMemberAccessibility.ensuredFor(nativeField);
    this.nativeField = nativeField;
  }
}
