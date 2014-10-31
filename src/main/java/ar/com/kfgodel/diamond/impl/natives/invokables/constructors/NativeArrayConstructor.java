package ar.com.kfgodel.diamond.impl.natives.invokables.constructors;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This type represents the native array constructor function
 * Created by kfgodel on 25/10/14.
 */
public class NativeArrayConstructor implements PolymorphicInvokable {

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
        return this.apply(arguments[0]);
    }

    @Override
    public Object apply(Object onlyArgument) {
        if(!(onlyArgument instanceof Number)){
            throw new IllegalArgumentException("Array constructor parameter must be an integer value but received: " + onlyArgument);
        }
        Number dimension = (Number) onlyArgument;
        return Array.newInstance(nativeArrayClass.getComponentType(), dimension.intValue());
    }
}
