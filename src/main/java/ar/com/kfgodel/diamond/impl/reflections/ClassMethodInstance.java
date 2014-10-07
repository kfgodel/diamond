package ar.com.kfgodel.diamond.impl.reflections;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

/**
 * This type represents a method that belongs to a class
 * Created by kfgodel on 18/09/14.
 */
public class ClassMethodInstance implements ClassMethod {

    private LazyValue<String> methodName;

    @Override
    public String name() {
        return methodName.get();
    }

    public static ClassMethodInstance create(MethodDescription description) {
        ClassMethodInstance nativeClassMethod = new ClassMethodInstance();
        nativeClassMethod.methodName = SuppliedValue.create(description.getName());
        return nativeClassMethod;
    }

}
