package ar.com.kfgodel.diamond.api.types.is;

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

}
