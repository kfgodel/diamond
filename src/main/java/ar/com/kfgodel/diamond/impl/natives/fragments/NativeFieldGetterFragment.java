package ar.com.kfgodel.diamond.impl.natives.fragments;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.Field;

/**
 * This type represents a native field getter function
 * <p>
 * Created by kfgodel on 23/10/14.
 */
public class NativeFieldGetterFragment {

  public static Object apply(Field nativeField, Object instance) {
    try {
      return nativeField.get(instance);
    } catch (IllegalAccessException e) {
      throw new DiamondException("The field is inaccessible: " + nativeField, e);
    } catch (IllegalArgumentException e) {
      throw new DiamondException("Get rejected for field[" + nativeField + "] on instance[" + instance + "]", e);
    } catch (NullPointerException e) {
      throw new DiamondException("Get for instance field[" + nativeField + "] cannot be done on null instance", e);
    } catch (ExceptionInInitializerError e) {
      throw new DiamondException("Get aborted for field[" + nativeField + "] due to a failed initialization", e);
    }
  }
}
