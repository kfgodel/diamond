package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.exceptions.HaltedConstructorInvocationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Function;

/**
 * This class represents the native constructor invoker to get new instances
 * Created by kfgodel on 25/10/14.
 */
public class NativeConstructorInvoker implements Function<Object[], Object> {

    private Constructor nativeConstructor;

    @Override
    public Object apply(Object[] arguments) {
        try {
            return nativeConstructor.newInstance(arguments);
        } catch (InstantiationException e) {
            throw new DiamondException("Constructor is for abstract type: " + nativeConstructor,e);
        } catch (IllegalAccessException e) {
            throw new DiamondException("Constructor is inaccessible: " + nativeConstructor,e);
        } catch (IllegalArgumentException e) {
            throw new DiamondException("Invocation rejected for constructor["+nativeConstructor+"] due to wrong arguments" + Arrays.toString(arguments),e);
        } catch (InvocationTargetException e) {
            throw new HaltedConstructorInvocationException(nativeConstructor, arguments, e);
        } catch (ExceptionInInitializerError e) {
            throw new DiamondException("Creation aborted for constructor["+nativeConstructor+"] due to a failed initialization",e);
        }
    }

    public static NativeConstructorInvoker create(Constructor nativeConstructor) {
        NativeMemberAccessibility.ensuredFor(nativeConstructor);
        NativeConstructorInvoker invoker = new NativeConstructorInvoker();
        invoker.nativeConstructor = nativeConstructor;
        return invoker;
    }

}
