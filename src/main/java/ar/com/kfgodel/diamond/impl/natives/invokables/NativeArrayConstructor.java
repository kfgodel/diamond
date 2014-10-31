package ar.com.kfgodel.diamond.impl.natives.invokables;

import ar.com.kfgodel.diamond.api.invokable.Invokable;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This type represents the native array constructor function
 * Created by kfgodel on 25/10/14.
 */
public class NativeArrayConstructor implements Invokable {

    private Class<?> nativeArrayClass;

    public static NativeArrayConstructor create(Class<?> nativeArrayClass) {
        NativeArrayConstructor constructor = new NativeArrayConstructor();
        constructor.nativeArrayClass = nativeArrayClass;
        return constructor;
    }

    @Override
    public Object invoke(Object... arguments) {
        if(arguments.length != 1){
            throw new IllegalArgumentException("Array constructor can have only one int parameter but received: " + Arrays.toString(arguments));
        }
        Object onlyArgument = arguments[0];
        if(!(onlyArgument instanceof Number)){
            throw new IllegalArgumentException("Array constructor parameter must be an integer value but received: " + onlyArgument);
        }
        Number dimension = (Number) onlyArgument;
        return Array.newInstance(nativeArrayClass.getComponentType(), dimension.intValue());
    }
}
