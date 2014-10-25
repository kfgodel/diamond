package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * This type represents a native field getter function
 *
 * Created by kfgodel on 23/10/14.
 */
public class NativeFieldGetter implements Function<Object, Object> {

    private Field nativeField;

    @Override
    public Object apply(Object instance) {
        try {
            return nativeField.get(instance);
        }catch (IllegalAccessException e) {
            throw new DiamondException("The field is inaccessible: " + nativeField, e);
        }catch(IllegalArgumentException e) {
            throw new DiamondException("Get rejected for field["+nativeField+"] on instance["+instance+"]", e);
        }catch(NullPointerException e) {
            throw new DiamondException("Get for instance field["+nativeField+"] cannot be done on null instance", e);
        }catch(ExceptionInInitializerError e){
            throw new DiamondException("Get aborted for field["+nativeField+"] due to a failed initialization", e);
        }
    }

    public static NativeFieldGetter create(Field nativeField) {
        NativeMemberAccessibility.ensuredFor(nativeField);
        NativeFieldGetter getter = new NativeFieldGetter();
        getter.nativeField = nativeField;
        return getter;
    }

}
