package ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.exceptions.HaltedMethodInvocationException;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.impl.natives.NativeMemberAccessibility;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * This type serves as base clase for method handle invocation variants
 * Created by kfgodel on 09/11/14.
 */
public abstract class MethodHandleInvokerSupport implements PolymorphicInvokable {

  protected MethodHandle methodHandle;
  private Method nativeMethod;

  @Override
  public Object invoke(Object... arguments) {
    if (arguments.length != getExpectedArgumentCount()) {
      throw new DiamondException("Invocation rejected for method[" + nativeMethod + "]. Expected " + getExpectedArgumentCount() + " arguments but invoked with: " + Arrays.toString(arguments));
    }
    try {
      return realInvocation(arguments);
    } catch (WrongMethodTypeException e) {
      throw new DiamondException("Invocation rejected for method[" + nativeMethod + "],  inapplicable to " + Arrays.toString(arguments), e);
    } catch (ClassCastException e) {
      throw new DiamondException("Invocation rejected for method[" + nativeMethod + "] failed conversion for arguments: " + Arrays.toString(arguments), e);
    } catch (Throwable e) {
      throw new HaltedMethodInvocationException(nativeMethod, arguments, e);
    }
  }

  protected abstract int getExpectedArgumentCount();

  protected void setNativeMethod(Method nativeMethod) {
    NativeMemberAccessibility.ensuredFor(nativeMethod);
    this.nativeMethod = nativeMethod;

    MethodHandle exactHandler = null;
    try {
      exactHandler = MethodHandles.lookup().unreflect(nativeMethod);
    } catch (IllegalAccessException e) {
      throw new DiamondException("The method[" + nativeMethod + "] is not accessible?", e);
    }
    this.methodHandle = exactHandler.asType(getExpectedMethodType());
  }

  protected abstract Object realInvocation(Object[] arguments) throws Throwable;

  protected abstract MethodType getExpectedMethodType();
}
