package ar.com.kfgodel.diamond.impl.reflections;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassMethodSource;
import ar.com.kfgodel.diamond.impl.sources.ClassDefinedClassMethodSourceImpl;

/**
 * This type represents a class instance based on a native class instance
 * Created by kfgodel on 18/09/14.
 */
public class NativeClass implements ClassInstance {

    private Class<?> classInstance;

    @Override
    public String name() {
        return classInstance.getSimpleName();
    }

    @Override
    public ClassDefinedClassMethodSource methods() {
        return ClassDefinedClassMethodSourceImpl.create(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(classInstance.getName());
        return builder.toString();
    }

    public static NativeClass create(Class<?> classInstance) {
        NativeClass nativeClass = new NativeClass();
        nativeClass.classInstance = classInstance;
        return nativeClass;
    }

}
