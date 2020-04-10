package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.behavior.NamedBehaviorSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;

/**
 * This type represents the source of bound methods from a meta-object
 * Created by kfgodel on 17/11/14.
 */
public interface BoundMethods extends NamedBehaviorSource<BoundMethod> {

  /**
   * @return All the class methods for the type
   */
  Nary<BoundMethod> all();

  /**
   * Retrieves this type methods that match the given name (including all of them, inherited and overloaded).<br>
   * It may be 0, 1 or more
   *
   * @param methodName The name for the searched methods
   * @return The nary of matching methods or an empty (if no match)
   */
  Nary<BoundMethod> named(String methodName);

  /**
   * Retrieves the methods of the type that matches name and parameter types (including inherited).<br>
   * It may be 0, 1, or more
   *
   * @param methodName     The name of the method to look for
   * @param parameterTypes The method parameter types
   * @return The nary of matching methods
   */
  Nary<BoundMethod> withSignature(String methodName, TypeInstance... parameterTypes);

  /**
   * Retrieves the methods of the type that matches name and parameter types (including inherited).<br>
   * It may be 0, 1, or more
   *
   * @param methodName           The name of the method to look for
   * @param nativeParameterTypes The method parameter types
   * @return The nary of matching methods
   */
  Nary<BoundMethod> withNativeSignature(String methodName, Type... nativeParameterTypes);

  /**
   * Retrieves the methods of the type that matches the given parameter types.<br>
   * The result may be 0, 1, or N elements
   *
   * @param paramTypes The type of method parameters declared
   * @return The found methods
   */
  @Override
  Nary<BoundMethod> withParameterTypes(TypeInstance... paramTypes);

}
