package ar.com.kfgodel.diamond.api.types.runtime;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the information about the types hierarchy in runtime
 * Date: 8/12/19 - 20:58
 */
public interface RuntimeTypeHierarchy {

  /**
   * @return The optional superclass of this instance. Or empty if this instance
   * represents either the Object class, an interface type, an array type, a primitive type, void,
   * or a variable type (like wildcard).<br>
   * The super class is the un-parameterized (raw) class instance that is the runtime super type of
   * this type
   */
  Nary<TypeInstance> superclass();
}
