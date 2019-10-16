package ar.com.kfgodel.diamond.api.behavior;

import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.naming.NamedSource;
import ar.com.kfgodel.diamond.api.parameters.ParameterizedSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;

/**
 * This type represents an abstraction of an instance or a type as containers of methods that can be retrieved or accessed
 * through them
 * Created by kfgodel on 17/11/14.
 */
public interface NamedBehaviorSource<T extends Named> extends NamedSource<T>, ParameterizedSource<T> {

  /**
   * @return All the behavior elements accessible from this instance
   */
  Nary<T> all();

  /**
   * Retrieves the behavior elements that match the given name.<br>
   * It may be 0, 1 or more
   *
   * @param methodName The name for the behavior element
   * @return The nary of matching elements or an empty (if no match)
   */
  Nary<T> named(String methodName);

  /**
   * Retrieves the behavior elements that match name and parameter types.<br>
   * It may be 0, 1, or more
   *
   * @param methodName     The name of the element to look for
   * @param parameterTypes The behavior parameter types
   * @return The nary of matching elements
   */
  Nary<T> withSignature(String methodName, TypeInstance... parameterTypes);

  /**
   * Retrieves the behavior elements that match name and native parameter types.<br>
   * It may be 0, 1, or more
   *
   * @param methodName           The name of the element to look for
   * @param nativeParameterTypes The element parameter types
   * @return The nary of matching elements
   */
  Nary<T> withNativeSignature(String methodName, Type... nativeParameterTypes);

  /**
   * Retrieves the behavior elements that match the given parameter types.<br>
   * The result may be 0, 1, or N elements
   *
   * @param paramTypes The type behavior parameters
   * @return The found elements
   */
  @Override
  Nary<T> withParameters(TypeInstance... paramTypes);

}
