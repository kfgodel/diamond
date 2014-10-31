package ar.com.kfgodel.diamond.impl.natives.invokables.methods;

import java.lang.reflect.Method;

/**
 * This type represents a method invoker for static methods
 * Created by kfgodel on 30/10/14.
 */
public class NativeStaticMethodInvoker extends NativeMethodInvokerSupport  {

    @Override
    public Object invoke(Object... arguments) {
        return this.invokeOn(null, arguments);
    }

    public static NativeStaticMethodInvoker create(Method nativeMethod) {
        NativeStaticMethodInvoker invoker = new NativeStaticMethodInvoker();
        invoker.setNativeMethod(nativeMethod);
        return invoker;
    }

}
