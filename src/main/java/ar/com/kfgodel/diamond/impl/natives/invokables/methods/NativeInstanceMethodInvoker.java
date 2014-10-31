package ar.com.kfgodel.diamond.impl.natives.invokables.methods;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.impl.natives.invokables.InstanceArguments;

import java.lang.reflect.Method;

/**
 * this type represents a method invoker for instance methods
 *
 * Created by kfgodel on 30/10/14.
 */
public class NativeInstanceMethodInvoker extends NativeMethodInvokerSupport implements Invokable {

    @Override
    public Object invoke(Object... arguments) {
        Object instance = null;
        try {
            instance = InstanceArguments.getInstanceFrom(arguments);
        } catch (IllegalArgumentException e) {
            throw new DiamondException("Instance invocation for method["+getNativeMethod()+"] cannot be done without an instance", e);
        }
        Object[] extraArguments = InstanceArguments.getExtraArgumentsFrom(arguments);
        return this.apply(instance, extraArguments);
    }

    public static NativeInstanceMethodInvoker create(Method nativeMethod) {
        NativeInstanceMethodInvoker invoker = new NativeInstanceMethodInvoker();
        invoker.setNativeMethod(nativeMethod);
        return invoker;
    }

}
