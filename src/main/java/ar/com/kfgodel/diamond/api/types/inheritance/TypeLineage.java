package ar.com.kfgodel.diamond.api.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

/**
 * This type represents linear set of types that compose the lineage of a type behavior. For classes this is the set
 * of parent classes up until Object.<br>
 * This is the list of type that define the behavior of the lowest member by single inheritance
 * <p>
 * Created by kfgodel on 19/09/14.
 */
public interface TypeLineage {

  /**
   * Calculates the extension line from the lowest descendant to its highest ancestor returning the
   * succession line (starting at the bottom).<br>
   * This is the equivalent of navigating the superclasses of a class (ignoring the interfaces),
   * but it includes type variables replacement if available
   * @return The ordered stream of all classes that are "extended" in this lineage
   * starting from the lowestDescendant to its highest supertype
   */
  Nary<TypeInstance> allExtendedTypes();

  /**
   * @return The complete set of interfaces that the lowest member inherits
   * directly or indirectly by its supertype members
   */
  Nary<TypeInstance> allImplementedTypes();

  /**
   * Calculates the tree of extended an implemented types in this lineage and returns them in an orderded
   * fashion from the bottom up, going Breadth first.<br>
   * <br>
   * Some guarantees:<br>
   * - There are no duplicates<br>
   * - The parameterized version is presented first (if any), and then the runtime counterpart
   * - The super class are presented before super interfaces
   * - lowest types (closer to the lowest descendant) are presented before higher
   * ancestors (the tree is explored from the lowest descendant to its
   * parent types per hierarchy level)
   *
   * @return All the types that are related to this lineage. The Nary includes all
   * the types that are inherited by the lowest descendant (and itself) and
   * their runtime representation.<br>
   */
  Nary<TypeInstance> allRelatedTypes();

  /**
   * @param descendant The class member of this lineage
   * @return The direct ancestor of the given class or empty if descendant
   * is already the highest ancestor, or not from this lineage
   */
  Unary<TypeInstance> ancestorOf(TypeInstance descendant);

  /**
   * @param ancestor The class member of this lineage
   * @return The direct descendant of the given class or empty if ancestor
   * is the lowest descendant, or not from this lineage
   */
  Unary<TypeInstance> descendantOf(TypeInstance ancestor);

  /**
   * Searches the relatedTypes of this lineage for the given reference type, and tries to get the generic arguments
   * used to parameterize it.<br>
   * If the given type is not part of the related types, or it has no type arguments, an empty nary is returned
   *
   * @param referenceType The type that is related to this lineage
   * @return The set of type arguments used to parameterize the reference type
   */
  Nary<TypeInstance> genericArgumentsOf(TypeInstance referenceType);

  /**
   * @return The highest member of this lineage
   */
  TypeInstance highestAncestor();

  /**
   * @return The lowest member of this lineage
   */
  TypeInstance lowestDescendant();
}
