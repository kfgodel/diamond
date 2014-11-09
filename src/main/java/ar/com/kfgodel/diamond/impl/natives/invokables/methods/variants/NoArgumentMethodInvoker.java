package ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants;

import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * This type represents the invoker of a no argument method (instance or static)
 * Created by kfgodel on 09/11/14.
 */
public class NoArgumentMethodInvoker extends  MethodHandleInvokerSupport {

    public static NoArgumentMethodInvoker create(Method nativeMethod) {
        NoArgumentMethodInvoker invoker = new NoArgumentMethodInvoker();
        invoker.setNativeMethod(nativeMethod);
        return invoker;
    }

    @Override
    protected int getExpectedArgumentCount() {
        return 0;
    }

    @Override
    protected Object realInvocation(Object[] arguments) throws Throwable {
        return methodHandle.invoke();
    }

    @Override
    protected MethodType getExpectedMethodType() {
        return MethodType.methodType(Object.class);
    }
}
