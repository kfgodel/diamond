package ar.com.kfgodel.diamond.api.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents linear set of types that compose the lineage of a type behavior. For classes this is the set
 * of parent classes up until Object.<br>
 *     This is the list of type that define the behavior of the lowest member by single inheritance
 *
 * Created by kfgodel on 19/09/14.
 */
public interface TypeLineage {

    /**
     * @return The lowest member of this lineage
     */
    TypeInstance lowestDescendant();

    /**
     * @return The highest member of this lineage
     */
    TypeInstance highestAncestor();

    /**
     * @return The ordered stream of all classes that belong to this lineage starting from the lowestDescendant to its highest supertype
     */
    Nary<TypeInstance> allMembers();

    /**
     * @param descendant The class member of this lineage
     * @return The direct ancestor of the given class or empty if descendant is already the highest ancestor, or not from this lineage
     */
    Nary<TypeInstance> ancestorOf(TypeInstance descendant);

    /**
     * @param ancestor The class member of this lineage
     * @return The direct descendant of the given class or empty if ancestor is the lowest descendant, or not from this lineage
     */
    Nary<TypeInstance> descendantOf(TypeInstance ancestor);

    /**
     * @return The complete set of interfaces that the lowest member inherits directly or indirectly by its supertype members
     */
    Nary<TypeInstance> inheritedInterfaces();

    /**
     * @return All the types that are related to this lineage. The Nary includes all the types that are inherited by the lowest descendant (and itself) and
     * their runtime representation.<br>
     * Some guarantees:<br>
     * - There are no duplicates<br>
     * - The parameterized version is presented first (if any), and then the runtime counterpart
     * - The super class are presented before super interfaces
     * - lowest types (closer to the lowest descendant) are presented before higher ancestors (the tree is explored from the lowest descendant to its
     *  parent types per hierarchy level)
     */
    Nary<TypeInstance> allRelatedTypes();
}
