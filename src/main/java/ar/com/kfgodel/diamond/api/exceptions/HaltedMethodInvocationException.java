package ar.com.kfgodel.diamond.api.exceptions;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * This type represents a method invocation exception
 *
 * Created by kfgodel on 25/10/14.
 */
public class HaltedMethodInvocationException extends DiamondException {

    private Throwable haltingCause;
    private Object[] invokedArguments;
    private Method invokedMethod;

    public HaltedMethodInvocationException(Method method, Object[] arguments, Throwable e) {
        super("Invocation halted for method["+method+"] and arguments"+ Arrays.toString(arguments) + ": " + e.getMessage(),e);
        this.haltingCause = e;
        this.invokedArguments = arguments;
        this.invokedMethod = method;
    }

    public Throwable getHaltingCause() {
        return haltingCause;
    }

    public Object getInvokedInstance() {
        if(invokedArguments.length > 0){
            return invokedArguments[0];
        }
        throw new DiamondException("There's no argument used as instance");
    }

    public Object[] getInvokedArguments() {
        return invokedArguments;
    }

    public Method getInvokedMethod() {
        return invokedMethod;
    }
}
