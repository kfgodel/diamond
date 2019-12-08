package ar.com.kfgodel.diamond.api.types.runtime;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the api to access the information about a type's runtime information
 *
 * Date: 8/12/19 - 20:39
 */
public interface TypeRuntime {

  /**
   * Returns the classes that represent this type in runtime.<br>
   * Objects that are instances of this type are created as instances of the given classes.<br>
   * <br>
   * Because wildcards and variables can have more than one upper bound the returned {@link Nary}
   * may contain more than one class.<br>
   * Types based on classes, parameterized types or arrays have only 1 runtime class.<br>
   * There could be types with no runtime classes at all<br>
   * <br>
   * @return The nary containing the class or classes for this type
   */
  Nary<Class<?>> classes();

  /**
   * @return The type used in runtime to represent this type. Usually it's the raw class version of this type.
   * For type variables it can be Object or its upper bound. For arrays it's the raw array class type
   */
  TypeInstance type();
}
