package ar.com.kfgodel.diamond.impl.methods.bound;

import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.BoundMethods;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.bound.BoundMembersSupport;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;

/**
 * This type represents the source of bound methods for a meta-object
 * Created by kfgodel on 17/11/14.
 */
public class BoundMethodsImpl extends BoundMembersSupport implements BoundMethods {

    private TypeMethods methodSource;

    public static BoundMethodsImpl create(TypeMethods typeMethods, Object instance) {
        BoundMethodsImpl methods = new BoundMethodsImpl();
        methods.setBindInstance(instance);
        methods.methodSource = typeMethods;
        return methods;
    }

    @Override
    public Nary<BoundMethod> all() {
        return boundVersionOf(methodSource.all());
    }

    @Override
    public Nary<BoundMethod> named(String methodName) {
        return boundVersionOf(methodSource.named(methodName));
    }

    @Override
    public Nary<BoundMethod> withSignature(String methodName, TypeInstance... parameterTypes) {
        return boundVersionOf(methodSource.withSignature(methodName, parameterTypes));
    }

    @Override
    public Nary<BoundMethod> withNativeSignature(String methodName, Type... nativeParameterTypes) {
        return boundVersionOf(methodSource.withNativeSignature(methodName, nativeParameterTypes));
    }

    @Override
    public Nary<BoundMethod> withParameters(TypeInstance... paramTypes) {
        return boundVersionOf(methodSource.withParameters(paramTypes));
    }

    @Override
    public Nary<BoundMethod> withNativeParameters(Type... parameterTypes) {
        return boundVersionOf(methodSource.withNativeParameters(parameterTypes));
    }
}
