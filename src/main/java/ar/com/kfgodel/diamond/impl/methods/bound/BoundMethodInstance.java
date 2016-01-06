package ar.com.kfgodel.diamond.impl.methods.bound;

import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.methods.equality.BoundMethodEquality;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(method.name());
        builder.append("() bound to ");
        builder.append(instance());
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return BoundMethodEquality.INSTANCE.areEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return BoundMethodEquality.INSTANCE.hash(this);
    }
}
