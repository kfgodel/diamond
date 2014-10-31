package ar.com.kfgodel.diamond.impl.natives.invokables.constructors;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.exceptions.HaltedConstructorInvocationException;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.impl.natives.NativeMemberAccessibility;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * This class represents the native constructor invoker to get new instances
 * Created by kfgodel on 25/10/14.
 */
public class NativeConstructorInvoker implements PolymorphicInvokable {

    private Constructor nativeConstructor;

    @Override
    public Object invoke(Object... arguments) {
        try {
            return nativeConstructor.newInstance(arguments);
        } catch (InstantiationException e) {
            throw new DiamondException("Abstract type cannot be created: " + nativeConstructor,e);
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
