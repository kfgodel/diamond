package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * This type represents a description of a diamond type and serves as an abstraction of the underlying represented type
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
    Supplier<Nary<Annotation>> getAnnotations();

    /**
     * @return The description of this type inheritance
     */
    InheritanceDescription getInheritanceDescription();

    /**
     * @return The supplier of type arguments
     */
    Supplier<Nary<TypeInstance>> getTypeArguments();

    /**
     * @return The supplier of type parameters for the described type
     */
    Supplier<Nary<TypeInstance>> getTypeParametersSupplier();

    /**
     * @return The supplier used to define the type component
     */
    Supplier<Nary<TypeInstance>> getComponentType();

    /**
     * @return The supplier that can reproduce the bounds of the described type
     */
    Supplier<TypeBounds> getBounds();

    /**
     * The set of methods that will be part of this type behavior
     * @return A stream to gather all methods
     */
    Supplier<Nary<TypeMethod>> getTypeMethods();

    /**
     * The set of fields that will be part of this type state
     * @return A stream to gather all fields
     */
    Supplier<Nary<TypeField>> getTypeFields();

    /**
     * The set of constructors that will be part of this type creators
     * @return A stream to gather all constructors
     */
    Supplier<Nary<TypeConstructor>> getTypeConstructors();

    /**
     * Indicates if this description is for a type that cannot be statically determined at compile type in every scope
     * (wildcards and type variables) and for that reason its class representation may be bounded to other type.
     * @return true if this type represents a type variable, a wildcard or annotated version of them
     */
    boolean isForVariableType();

    /**
     * @return The supplier of the optional package for the type
     */
    Supplier<Nary<TypePackage>> getDeclaredPackage();

    /**
     * @return The supplier of the raw classes for the runtime descriptio nof the type
     */
    Supplier<Nary<Class<?>>> getRawClassesSupplier();
}
