package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.sources.TypeMethods;
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
     * @return The information about this type generification.<br>
     *     TypeGenerics holds the relationships between this type and its parameters, arguments and bounds
     */
    TypeGenerics generics();

    /**
     * @return The component type of this container type.<br>
     * Component type is only present on arrays that reify their component types
     */
    Optional<TypeInstance> componentType();

    /**
     * @return The information about this type inheritance.<br>
     *     TypeInheritance holds the relationships between this type and its parent types
     */
    TypeInheritance inheritance();

    /**
     * @return The information about this type methods.<br>
     *     TypeMethods holds the relationship between this type and the methods that instances of this type understand
     */
    TypeMethods methods();

}
