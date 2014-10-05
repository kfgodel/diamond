package ar.com.kfgodel.diamond.impl.reflections;

import ar.com.kfgodel.diamond.api.ClassMethod;

import java.lang.reflect.Method;

/**
 * This type represents a method in a class
 * Created by kfgodel on 18/09/14.
 */
public class NativeClassMethod implements ClassMethod {

    private Method methodInstance;

    @Override
    public String name() {
        return methodInstance.getName();
    }

    public static NativeClassMethod create(Method methodInstance) {
        NativeClassMethod nativeClassMethod = new NativeClassMethod();
        nativeClassMethod.methodInstance = methodInstance;
        return nativeClassMethod;
    }

}
