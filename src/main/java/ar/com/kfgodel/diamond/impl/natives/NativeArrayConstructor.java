package ar.com.kfgodel.diamond.impl.natives;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

/**
 * This type represents the native array constructor function
 * Created by kfgodel on 25/10/14.
 */
public class NativeArrayConstructor implements Function<Object[], Object> {

    private Class<?> nativeArrayClass;

    @Override
    public Object apply(Object[] objects) {
        if(objects.length != 1){
            throw new IllegalArgumentException("Array constructor can have only one int parameter but received: " + Arrays.toString(objects));
        }
        Object onlyArgument = objects[0];
        if(!(onlyArgument instanceof Number)){
            throw new IllegalArgumentException("Array constructor parameter must be an integer value but received: " + onlyArgument);
        }
        Number dimension = (Number) onlyArgument;
        return Array.newInstance(nativeArrayClass.getComponentType(), dimension.intValue());
    }

    public static NativeArrayConstructor create(Class<?> nativeArrayClass) {
        NativeArrayConstructor constructor = new NativeArrayConstructor();
        constructor.nativeArrayClass = nativeArrayClass;
        return constructor;
    }

}
