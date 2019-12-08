package ar.com.kfgodel.diamond.api.types.is;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;

/**
 * This interface defines the different verifications that can be done on a type
 *
 * Date: 8/12/19 - 17:55
 */
public interface TypeTests {

  /**
   * Answers if this type belongs to the given kind. Kinds are groups of types
   * usually defined by the language designers
   *
   * @param testedKind The kind to test this type on
   * @return true if this type can be considered of the given kind
   */
  boolean ofKind(Kind testedKind);

  /**
   * Answers if this type is a cast-safe supertype of the given type
   *
   * @param possibleSubtype other type to test the relation
   * @return true if an object of the other type can be assigned to a variable of this type
   */
  boolean assignableFrom(TypeInstance possibleSubtype);

  /**
   * Answers if this type can be assigned to the other type
   *
   * @param possibleSuperType other type to test the relation
   * @return true if an object of this type can be assigned to a variable of the other type
   */
  boolean assignableTo(TypeInstance possibleSuperType);

  /**
   * Indicates if the given object is assignable to this type.<br>
   * Or this type is part if the object's type hierarchy. This method follows the same
   * semantics as Class.isInstance().<br>
   * <br>
   * For upper bounded types with more than one bound this is true if any of the upper bounds is true.
   *
   * @param anObject The object to test
   * @return true if the given object can be casted to this type without an exception,
   * false if object is null or otherwise
   */
  boolean typeFor(Object anObject);


}
