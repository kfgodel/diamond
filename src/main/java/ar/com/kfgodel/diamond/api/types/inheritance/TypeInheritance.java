package ar.com.kfgodel.diamond.api.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the information about a type inheritance an its relations with other super types
 * Created by kfgodel on 05/10/14.
 */
public interface TypeInheritance {

  /**
   * @return The extended supertype of this type (declared with extends). Or empty if this type
   * represents either the Object class, an interface type, an array type, a primitive type, void,
   * or a variable type (like wildcard).<br>
   * The extended type is the parent class with correct type arguments assigned from this type,
   * thus is the compile time parent type of this type.
   */
  Nary<TypeInstance> extendedType();

  /**
   * @return The set of interfaces implemented by this type under the compile type definition (with type arguments).<br>
   * Can be empty if this type has no interface implementation
   */
  Nary<TypeInstance> implementedTypes();

  /**
   * Returns this type lineage (starting from this type, the set of extended types up until Object).<br>
   * This lineage does not follow class relationships but parameterized types relationships.<br>
   * In this lineage you will find compile time related types
   *
   * @return The type lineage of this type
   */
  TypeLineage typeLineage();

  /**
   * Returns the set of supertypes composed by the super class and any directly implemented interface.<br>
   * This method allows navigating the supertype tree of a type.<br>
   *
   * @return A nary composed of the superclass and the interfaces
   */
  Nary<TypeInstance> supertypes();

}
