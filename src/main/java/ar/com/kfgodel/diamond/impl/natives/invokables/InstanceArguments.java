package ar.com.kfgodel.diamond.impl.natives.invokables;

import java.util.Arrays;

/**
 * This type represents the interpretation of arguments for an instance invocation.<br>
 * Invocations over instance members require the instance to be separated and treated specially, this type
 * splits the arguments separating the instance
 * Created by kfgodel on 30/10/14.
 */
public class InstanceArguments {


  public static Object getInstanceFrom(Object[] arguments) throws IllegalArgumentException {
    checkLength(arguments);
    return arguments[0];
  }

  public static Object[] getExtraArgumentsFrom(Object[] arguments) throws IllegalArgumentException {
    checkLength(arguments);
    Object[] split = new Object[arguments.length - 1];
    System.arraycopy(arguments, 1, split, 0, split.length);
    return split;
  }

  private static void checkLength(Object[] arguments) {
    if (arguments.length < 1) {
      throw new IllegalArgumentException("At least one argument expected as instance: " + Arrays.toString(arguments));
    }
  }

  public static Object[] join(Object instance, Object[] arguments) {
    Object[] joined = new Object[arguments.length + 1];
    joined[0] = instance;
    System.arraycopy(arguments, 0, joined, 1, arguments.length);
    return joined;
  }
}
