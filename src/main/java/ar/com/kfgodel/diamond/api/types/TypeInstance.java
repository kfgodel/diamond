package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.sources.TypeDefinedTypeNamesSource;

import java.util.Optional;
import java.util.stream.Stream;

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
    TypeDefinedTypeNamesSource names();

    /**
     * @return The type boundaries of this type (if any)
     * Wildcards and type variables usually have boundaries other types don't
     */
    TypeBounds bounds();

    /**
     * @return The type of component this container type has.<br>
     * Component type is only present on arrays that reify their component types
     */
    Optional<TypeInstance> componentType();

    /**
     * @return The type argumunts used to parameterize this instance (if any).
     * Parameterized types have actual type arguments that are preserved through reflection (on certain context)
     */
    Stream<TypeInstance> typeArguments();
}
