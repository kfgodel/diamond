package ar.com.kfgodel.diamond.api.types.runtime;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
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

  /**
   * @return The set of interfaces implemented by this type. This can be empty if this instance
   * represents a type that cannot implement interfaces or doesn't implement any.<br>
   * The instances returned are un-parameterized (raw version) types and corresponds to the
   * runtime representation of the implemented types
   */
  Nary<TypeInstance> interfaces();

  /**
   * Returns this type lineage (starting from this type, the set of super types up until Object, or ancestors).<br>
   * This lineage follow class relationships with superclasses and interfaces. It does not replace type
   * variables as it moves forward.<br>
   * In this lineage you will find runtime type relationships
   *
   * @return The type lineage of this type
   */
  TypeLineage lineage();

  /**
   * Returns the set of supertypes composed by the super class and any directly implemented interface.<br>
   * This method allows navigating the supertype tree of a type.<br>
   *
   * @return A nary composed of the superclass and the interfaces
   */
  Nary<TypeInstance> supertypes();
}
