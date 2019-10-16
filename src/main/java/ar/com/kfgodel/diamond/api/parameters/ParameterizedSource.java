package ar.com.kfgodel.diamond.api.parameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;

/**
 * This type represents the source of types that are parameterized
 * Created by kfgodel on 07/11/14.
 */
public interface ParameterizedSource<T> {

  /**
   * Returns the element from this sources that matches the given parameters.<br>
   * Depending on the source, and the elem1ents this may be 0, 1, or N elements
   *
   * @param paramTypes The type of constructor arguments declared for the constructor
   * @return The constructor that matches the given types or empty optional
   */
  Nary<T> withParameters(TypeInstance... paramTypes);


  /**
   * Returns the element from this sources that matches the given parameters.<br>
   * Depending on the source, and the elem1ents this may be 0, 1, or N elements
   *
   * @param parameterTypes The type of constructor arguments declared for the constructor
   * @return The constructor that matches the given types or empty optional
   */
  Nary<T> withNativeParameters(Type... parameterTypes);
}
