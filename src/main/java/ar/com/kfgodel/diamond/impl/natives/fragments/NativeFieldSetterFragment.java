package ar.com.kfgodel.diamond.impl.natives.fragments;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.Field;

/**
 * This type represents the setter consumer to modify the value of a native field
 *
 * Created by kfgodel on 23/10/14.
 */
public class NativeFieldSetterFragment {

    public static void accept(Field nativeField, Object instance, Object value) throws DiamondException {
        try {
            nativeField.set(instance, value);
        }catch (IllegalAccessException e) {
            throw new DiamondException("The field is inaccessible or final: " + nativeField, e);
        }catch(IllegalArgumentException e) {
            throw new DiamondException("Set rejected for field["+nativeField+"] with value["+value+"] on instance["+instance+"]", e);
        }catch(NullPointerException e) {
            throw new DiamondException("Set for instance field["+nativeField+"] cannot be done on null instance", e);
        }catch(ExceptionInInitializerError e){
            throw new DiamondException("Set aborted for field["+nativeField+"] due to a failed initialization", e);
        }
    }
}
