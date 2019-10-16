package ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants;

import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * This type represents the invoker of a two argument method (instance or static)
 * Created by kfgodel on 09/11/14.
 */
public class TwoArgumentsMethodInvoker extends MethodHandleInvokerSupport {

  @Override
  protected int getExpectedArgumentCount() {
    return 2;
  }

  protected Object realInvocation(Object[] arguments) throws Throwable {
    return methodHandle.invoke(arguments[0], arguments[1]);
  }

  protected MethodType getExpectedMethodType() {
    return MethodType.methodType(Object.class, Object.class, Object.class);
  }

  public static TwoArgumentsMethodInvoker create(Method nativeMethod) {
    TwoArgumentsMethodInvoker invoker = new TwoArgumentsMethodInvoker();
    invoker.setNativeMethod(nativeMethod);
    return invoker;
  }

}
