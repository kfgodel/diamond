package ar.com.kfgodel.diamond.api.classes;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the classes that compose the lineage of a class behavior, from that specific subclass to Object
 * Created by kfgodel on 19/09/14.
 */
public interface TypeLineage {
    /**
     * @return The lower descendant of this lineage
     */
    TypeInstance lowestDescendant();

    /**
     * @return The upper ancestor of this lineage
     */
    TypeInstance highestAncestor();

    /**
     * @return The ordered stream of all classes that belong to this lineage starting from the lowestDescendant
     */
    Stream<TypeInstance> allMembers();

    /**
     * @param descendant The class member of this lineage
     * @return The direct ancestor of the given class or empty if descendant is already the highest ancestor, or not from this lineage
     */
    Optional<TypeInstance> ancestorOf(TypeInstance descendant);

    /**
     * @param ancestor The class member of this lineage
     * @return THe direct descendant of the given class or empty if ancestor is the lowest descendant, or not from this lineage
     */
    Optional<TypeInstance> descendantOf(TypeInstance ancestor);

}
