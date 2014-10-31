package ar.com.kfgodel.diamond.impl.natives.invokables.methods;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.impl.natives.NativeMemberAccessibility;
import ar.com.kfgodel.diamond.impl.natives.fragments.NativeMethodInvokerFragment;

import java.lang.reflect.Method;

/**
 * This type serves as a base class for native method invokers
 * Created by kfgodel on 30/10/14.
 */
public abstract class NativeMethodInvokerSupport implements PolymorphicInvokable {

    private Method nativeMethod;

    protected Object invokeOn(Object instance, Object[] arguments) {
        return NativeMethodInvokerFragment.apply(nativeMethod, instance, arguments);
    }

    protected void setNativeMethod(Method nativeMethod) {
        NativeMemberAccessibility.ensuredFor(nativeMethod);
        this.nativeMethod = nativeMethod;
    }


    public Method getNativeMethod() {
        return nativeMethod;
    }
}
