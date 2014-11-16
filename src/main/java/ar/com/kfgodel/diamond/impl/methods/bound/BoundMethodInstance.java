package ar.com.kfgodel.diamond.impl.methods.bound;

import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;

/**
 * This type represents a method bound to an instance
 * Created by kfgodel on 16/11/14.
 */
public class BoundMethodInstance implements BoundMethod {

    private TypeMethod method;
    private Object instance;

    @Override
    public TypeMethod typeMethod() {
        return method;
    }

    @Override
    public Object instance() {
        return instance;
    }

    @Override
    public Object invoke(Object... arguments) {
        return method.invokeOn(instance, arguments);
    }

    public static BoundMethodInstance create(TypeMethod method, Object instance) {
        BoundMethodInstance boundMethod = new BoundMethodInstance();
        boundMethod.method = method;
        boundMethod.instance = instance;
        return boundMethod;
    }

    @Override
    public String name() {
        return method.name();
    }
}
