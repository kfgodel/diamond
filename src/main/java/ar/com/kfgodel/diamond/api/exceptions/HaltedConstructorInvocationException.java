package ar.com.kfgodel.diamond.api.exceptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * This type represents a method invocation exception
 * <p>
 * Created by kfgodel on 25/10/14.
 */
public class HaltedConstructorInvocationException extends DiamondException {

  private Throwable haltingCause;
  private Object[] invokedArguments;
  private Constructor invokedConstructor;

  public HaltedConstructorInvocationException(Constructor constructor, Object[] arguments, InvocationTargetException e) {
    super("Invocation halted for constructor[" + constructor + "] with arguments" + Arrays.toString(arguments) + ": " + e.getCause().getMessage(), e);
    this.haltingCause = e.getCause();
    this.invokedArguments = arguments;
    this.invokedConstructor = constructor;
  }

  public Throwable getHaltingCause() {
    return haltingCause;
  }

  public Object[] getInvokedArguments() {
    return invokedArguments;
  }

  public Constructor getInvokedConstructor() {
    return invokedConstructor;
  }
}
