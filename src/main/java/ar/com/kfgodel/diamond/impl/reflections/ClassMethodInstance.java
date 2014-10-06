package ar.com.kfgodel.diamond.impl.reflections;

import ar.com.kfgodel.diamond.api.ClassMethod;

import java.lang.reflect.Method;

/**
 * This type represents a method that belongs to a class
 * Created by kfgodel on 18/09/14.
 */
public class ClassMethodInstance implements ClassMethod {

    private String methodName;

    @Override
    public String name() {
        return methodName;
    }

    public static ClassMethodInstance create(Method methodInstance) {
        ClassMethodInstance nativeClassMethod = new ClassMethodInstance();
        nativeClassMethod.methodName = methodInstance.getName();
        return nativeClassMethod;
    }

}
