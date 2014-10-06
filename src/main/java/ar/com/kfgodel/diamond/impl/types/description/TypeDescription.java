package ar.com.kfgodel.diamond.impl.types.description;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a description of a type and serves as a common interface for every native and extend types
 * Created by kfgodel on 28/09/14.
 */
public interface TypeDescription {

    /**
     * @return The names given to the type
     */
    Supplier<TypeNames> getNames();

    /**
     * @return The supplier of annotations of the described type
     */
    Supplier<Stream<Annotation>> getAnnotations();

    /**
     * @return The supplier of superclass for this type
     */
    Supplier<Optional<TypeInstance>> getSuperclassSupplier();

    /**
     * @return The supplier of extended types
     */
    Supplier<Optional<TypeInstance>> getExtendedTypeSupplier();

    /**
     * @return The supplier of type arguments
     */
    Supplier<Stream<TypeInstance>> getTypeArguments();

    /**
     * @return The supplier of type parameters for the described type
     */
    Supplier<Stream<TypeInstance>> getTypeParametersSupplier();

    /**
     * @return The supplier used to define the type component
     */
    Supplier<Optional<TypeInstance>> getComponentType();

    /**
     * @return The supplier that can reproduce the bounds of the described type
     */
    Supplier<TypeBounds> getBounds();

    /**
     * The set of methods that will be part of this type
     * @return A stream to gather all methods
     */
    Supplier<Stream<ClassMethod>> getTypeMethods();

    /**
     * Indicates if this description is for a type that cannot be statically determined at compile type in every scope
     * (wildcards and type variables) and for that reason its class representation may be bounded to other type.
     * @return true if this type represents a type variable, a wildcard or annotated version of them
     */
    boolean isForVariableType();

}
