package ar.com.kfgodel.diamond.impl.natives.fragments;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.exceptions.HaltedMethodInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * This type represents the native method invoker that can be used to invoke native methods
 * Created by kfgodel on 25/10/14.
 */
public class NativeMethodInvokerFragment {

    private Method nativeMethod;

    public static Object apply(Method nativeMethod, Object instance, Object[] arguments) {
        try {
            return nativeMethod.invoke(instance, arguments);
        } catch (IllegalAccessException e) {
            throw new DiamondException("Method is inaccessible: " + nativeMethod,e);
        } catch (IllegalArgumentException e) {
            // We try to describe the error better (depending on the cause)
            if(Modifier.isStatic(nativeMethod.getModifiers())){
                throw new DiamondException("Invocation rejected for method["+nativeMethod+"] due to wrong arguments" + Arrays.toString(arguments),e);
            }else{
                throw new DiamondException("Invocation rejected for method["+nativeMethod+"] because instance is not applicable["+instance+"] or wrong arguments" + Arrays.toString(arguments),e);
            }
        } catch (InvocationTargetException e) {
            throw new HaltedMethodInvocationException(nativeMethod, arguments, e);
        } catch (NullPointerException e) {
            throw new DiamondException("Instance invocation for method["+nativeMethod+"] cannot be done on null instance",e);
        } catch (ExceptionInInitializerError e) {
            throw new DiamondException("Invocation aborted for method["+nativeMethod+"] due to a failed initialization",e);
        }
    }

}
