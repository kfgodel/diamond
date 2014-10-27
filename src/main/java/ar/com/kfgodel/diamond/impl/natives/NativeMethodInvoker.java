package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.exceptions.HaltedMethodInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * This type represents the native method invoker that can be used to invoke native methods
 * Created by kfgodel on 25/10/14.
 */
public class NativeMethodInvoker implements BiFunction<Object,Object[],Object> {

    private Method nativeMethod;

    @Override
    public Object apply(Object instance, Object[] arguments) {
        try {
            return nativeMethod.invoke(instance, arguments);
        } catch (IllegalAccessException e) {
            throw new DiamondException("Method is inaccessible: " + nativeMethod,e);
        } catch (IllegalArgumentException e) {
            throw new DiamondException("Invocation rejected for method["+nativeMethod+"] because instance is invalid["+instance+"] or wrong arguments" + Arrays.toString(arguments),e);
        } catch (InvocationTargetException e) {
            throw new HaltedMethodInvocationException(nativeMethod, instance, arguments, e);
        } catch (NullPointerException e) {
            throw new DiamondException("Instance invocation for method["+nativeMethod+"] cannot be done on null instance",e);
        } catch (ExceptionInInitializerError e) {
            throw new DiamondException("Invocation aborted for method["+nativeMethod+"] due to a failed initialization",e);
        }
    }

    public static NativeMethodInvoker create(Method nativeMethod) {
        NativeMemberAccessibility.ensuredFor(nativeMethod);
        NativeMethodInvoker invoker = new NativeMethodInvoker();
        invoker.nativeMethod = nativeMethod;
        return invoker;
    }

}
