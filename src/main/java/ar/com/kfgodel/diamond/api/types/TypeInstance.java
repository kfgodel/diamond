package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.sources.TypeNames;

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
    TypeNames names();

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
     * @return The actual type arguments used to parameterize this type (if any).
     * Parameterized types have type arguments that are preserved through reflection (only on certain cases).<br>
     *     See typeParameters for the list of type variables that these arguments correspond to
     */
    Stream<TypeInstance> typeArguments();

    /**
     * @return The generic type parameters this type accepts (if any).
     * Parameterized classes have type variables that can be parameterized. This is the list of type variables.<br>
     *     See typeArguments for the actual type values of this variables
     */
    Stream<TypeInstance> typeParameters();


    /**
     * @return The extended supertype of this type (declared with extends). Or empty if this type
     *  represents either the Object class, an interface type, an array type, a primitive type, void,
     *  or a variable type (like wildcard).<br>
     *  The extended type is the parent class with correct type arguments assigned from this type,
     *  thus is the compile time parent type of this type.
     */
    Optional<TypeInstance> extendedType();
}
