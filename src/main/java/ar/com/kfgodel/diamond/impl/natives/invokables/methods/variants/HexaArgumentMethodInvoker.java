package ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants;

import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * This type represents the invoker of a 4 argument method (instance or static)
 * Created by kfgodel on 09/11/14.
 */
public class HexaArgumentMethodInvoker extends MethodHandleInvokerSupport {

  public static HexaArgumentMethodInvoker create(Method nativeMethod) {
    HexaArgumentMethodInvoker invoker = new HexaArgumentMethodInvoker();
    invoker.setNativeMethod(nativeMethod);
    return invoker;
  }

  @Override
  protected int getExpectedArgumentCount() {
    return 6;
  }

  @Override
  protected Object realInvocation(Object[] arguments) throws Throwable {
    return methodHandle.invoke(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4], arguments[5]);
  }

  @Override
  protected MethodType getExpectedMethodType() {
    return MethodType.methodType(Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class);
  }
}
