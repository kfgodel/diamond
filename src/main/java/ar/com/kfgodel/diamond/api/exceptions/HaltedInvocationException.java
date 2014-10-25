package ar.com.kfgodel.diamond.api.exceptions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * This type represents a method invocation exception
 *
 * Created by kfgodel on 25/10/14.
 */
public class HaltedInvocationException extends DiamondException {

    private Throwable haltingCause;
    private Object invokedInstance;
    private Object[] invokedArguments;
    private Method invokedMethod;

    public HaltedInvocationException(Method method, Object instance, Object[] arguments, InvocationTargetException e) {
        super("Invocation halted for method["+method+"] on instance["+instance+"] and arguments"+ Arrays.toString(arguments) + ": " + e.getCause().getMessage(),e);
        this.haltingCause = e.getCause();
        this.invokedArguments = arguments;
        this.invokedInstance = instance;
        this.invokedMethod = method;
    }

    public Throwable getHaltingCause() {
        return haltingCause;
    }

    public Object getInvokedInstance() {
        return invokedInstance;
    }

    public Object[] getInvokedArguments() {
        return invokedArguments;
    }

    public Method getInvokedMethod() {
        return invokedMethod;
    }
}
