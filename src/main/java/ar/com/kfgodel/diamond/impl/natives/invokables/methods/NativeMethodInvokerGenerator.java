package ar.com.kfgodel.diamond.impl.natives.invokables.methods;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.HeptaArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.HexaArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.NoArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.OctaArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.PentaArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.SingleArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.TetraArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.TriArgumentMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.variants.TwoArgumentsMethodInvoker;

import java.lang.reflect.Method;

/**
 * This type represents the generator of invokers for native methods that needs the evaluate the number of arguments
 * to use method handles
 * Created by kfgodel on 09/11/14.
 */
public class NativeMethodInvokerGenerator {

  public static final NativeMethodInvokerGenerator INSTANCE = new NativeMethodInvokerGenerator();

  public PolymorphicInvokable generateFor(Method nativeMethod) {
    int expectedArgumentCount = nativeMethod.getParameterCount();
    if (!java.lang.reflect.Modifier.isStatic(nativeMethod.getModifiers())) {
      // Instance methods require the extra instance as argument
      expectedArgumentCount++;
    }

    switch (expectedArgumentCount) {
      case 0:
        return NoArgumentMethodInvoker.create(nativeMethod);
      case 1:
        return SingleArgumentMethodInvoker.create(nativeMethod);
      case 2:
        return TwoArgumentsMethodInvoker.create(nativeMethod);
      case 3:
        return TriArgumentMethodInvoker.create(nativeMethod);
      case 4:
        return TetraArgumentMethodInvoker.create(nativeMethod);
      case 5:
        return PentaArgumentMethodInvoker.create(nativeMethod);
      case 6:
        return HexaArgumentMethodInvoker.create(nativeMethod);
      case 7:
        return HeptaArgumentMethodInvoker.create(nativeMethod);
      case 8:
        return OctaArgumentMethodInvoker.create(nativeMethod);
      default:
        throw new DiamondException("Method[" + nativeMethod + "] cannot be represented due to too many arguments. Consider using a Parameter Object pattern");
    }
  }

}
