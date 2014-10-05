package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.classes.TypeLineage;
import ar.com.kfgodel.diamond.api.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.sources.TypeMethodSource;
import ar.com.kfgodel.diamond.api.sources.TypeNames;

import java.util.Optional;

/**
 * This type represents the basic interface common to all types.<br>
 * Instances of this type represent one of the possible type in Java language including type variables and wildcards.<br>
 * <br>
 * Methods in this type are based on information available in subtypes of Java Type
 *
 * Created by kfgodel on 20/09/14.
 */
public interface TypeInstance extends Named, Annotated {

    /**
     * Returns the accessor object for this type names (in all their varieties)
     * @return The source of type names for this instance
     */
    TypeNames names();

    /**
     * The name that identifies this type with its annotations and generics information.<br> This name could be used to declare
     * this exact type in source code including actual type arguments
     * @return The name as a full type declaration (This is equivalent to the source code for this type declaration)
     */
    String declaration();


    /**
     * @return The type of component this container type has.<br>
     * Component type is only present on arrays that reify their component types
     */
    Optional<TypeInstance> componentType();


    /**
     * @return The information about this type generification.<br>
     *     This instance hold the relationships between this type and its parameters, arguments and bounds
     */
    TypeGenerics generics();

    /**
     * @return The extended supertype of this type (declared with extends). Or empty if this type
     *  represents either the Object class, an interface type, an array type, a primitive type, void,
     *  or a variable type (like wildcard).<br>
     *  The extended type is the parent class with correct type arguments assigned from this type,
     *  thus is the compile time parent type of this type.
     */
    Optional<TypeInstance> extendedType();

    /**
     * @return The optional superclass of this instance. Or empty if this type
     *  represents either the Object class, an interface type, an array type, a primitive type, void,
     *  or a variable type (like wildcard).<br>
     *     The super class is the un-parameterized (raw) class instance that is the runtime super type of
     *     this type
     */
    Optional<TypeInstance> superclass();

    /**
     * Returns this type lineage (starting from this type, the set of extended types up until Object).<br>
     *     This lineage does not follow class relationships but parameterized types relationships.<br>
     *     In this lineage you will find compile time related types
     * @return The type lineage of this type
     */
    TypeLineage typeLineage();

    /**
     * Returns this class lineage (starting from this type, the set of inherited classes up until Object).<br>
     *     This lineage does have type parameters coherency, because follows raw class inheritance.<br>
     *     In this lineage you will find runtime tim related types
     * @return The class lineage of this type
     */
    TypeLineage classLineage();


    /**
     * Returns the accessor object for class methods of this instance
     * @return The source of methods
     */
    TypeMethodSource methods();

}
