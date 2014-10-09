package ar.com.kfgodel.diamond.impl.reflections;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

/**
 * This type represents a method that belongs to a class
 * Created by kfgodel on 18/09/14.
 */
public class ClassMethodInstance implements ClassMethod {

    private LazyValue<String> methodName;
    private LazyValue<TypeInstance> returnType;

    @Override
    public String name() {
        return methodName.get();
    }

    @Override
    public TypeInstance returnType() {
        return returnType.get();
    }

    public static ClassMethodInstance create(MethodDescription description) {
        ClassMethodInstance classMethod = new ClassMethodInstance();
        classMethod.methodName = SuppliedValue.create(description.getName());
        classMethod.returnType = SuppliedValue.create(description.getReturnType());
        return classMethod;
    }

}
