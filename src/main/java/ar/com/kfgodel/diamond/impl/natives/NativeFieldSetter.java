package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;

/**
 * This type represents the setter consumer to modify the value of a native field
 *
 * Created by kfgodel on 23/10/14.
 */
public class NativeFieldSetter implements BiConsumer<Object, Object> {

    private Field nativeField;

    @Override
    public void accept(Object instance, Object value) throws DiamondException {
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

    public static NativeFieldSetter create(Field nativeField) {
        NativeMemberAccessibility.ensuredFor(nativeField);
        NativeFieldSetter setter = new NativeFieldSetter();
        setter.nativeField = nativeField;
        return setter;
    }

}
