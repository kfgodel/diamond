package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.stream.Stream;

/**
 * This type represents the possible sources for a type instance
 * Created by kfgodel on 20/09/14.
 */
public interface TypeSources {

    /**
     * Retrieves a type instance from a native type variable
     * @param typeVariable The variable that represents a possible type
     * @return The diamond instance to represent the variable
     */
    TypeInstance from(TypeVariable<?> typeVariable);

    /**
     * Retrieves a type instance from a native parameterized type
     * @param parameterizedType The parameterized type that represents a type
     * @return The diamond representation
     */
    TypeInstance from(ParameterizedType parameterizedType);

    /**
     * Retrieves a type instance from a parameterized type
     * @param wildcardType The wildcard that represents a type
     * @return The diamond representation
     */
    TypeInstance from(WildcardType wildcardType);

    /**
     * Retrieves a type instance from a parameterized type
     * @param genericArrayType The generic array that represents a type
     * @return The diamond representation
     */
    TypeInstance from(GenericArrayType genericArrayType);

    /**
     * Retrieves a type instance from a parameterized type
     * @param aClass The native parameterized type
     * @return The diamond representation
     */
    ClassInstance from(Class<?> aClass);

    /**
     * Retrieves a type instance from an object that represents a type wildcard and its annotations
     * @param annotatedWildCard The native annotated Type wildcard
     * @return The diamond representation of the type (with its annotations)
     */
    TypeInstance from(AnnotatedWildcardType annotatedWildCard);
    /**
     * Retrieves a type instance from an object that represents a type variable and its annotations
     * @param annotatedTypeVariable The native annotated Type variable
     * @return The diamond representation of the type (with its annotations)
     */
    TypeInstance from(AnnotatedTypeVariable annotatedTypeVariable);
    /**
     * Retrieves a type instance from an object that represents a parameterized type and its annotations
     * @param annotatedParameterized The native annotated parameterized Type
     * @return The diamond representation of the type (with its annotations)
     */
    TypeInstance from(AnnotatedParameterizedType annotatedParameterized);
    /**
     * Retrieves a type instance from an object that represents a generic array type and its annotations
     * @param annotatedArrayType The native annotated array Type
     * @return The diamond representation of the type (with its annotations)
     */
    TypeInstance from(AnnotatedArrayType annotatedArrayType);

    /**
     * Retrieves a type instance from a native class and its attached annotations
     * @param aClass The native parameterized type
     * @param annotations The attached annotations
     * @return The diamond representation
     */
    ClassInstance from(Class<?> aClass, Annotation[] annotations);

    /**
     * Retrieves a type instance from a native class and its type arguments.<br>
     *     As classes don't have type arguments and they come from a different source this method is needed on
     *     certain scenarios to get the parameterized class
     * @param aClass The native class representation
     * @param typeArguments The actual arguments for the class generic parameters
     * @return The type that represents it
     */
    ClassInstance from(Class<?> aClass, Stream<TypeInstance> typeArguments);

    /**
     * Retrieves a type instance from an unspecific type
     * @param type The type to get the type representation from
     * @return An instance that represents the type
     */
    TypeInstance fromUnspecific(Type type);

    /**
     * Retrieves a type instance from an unspecific annotated type (a type and its annotations)
     * @param annotatedType The native annotatedType
     * @return The diamond representation of the type (with its annotations)
     */
    TypeInstance fromUnspecific(AnnotatedType annotatedType);


}
