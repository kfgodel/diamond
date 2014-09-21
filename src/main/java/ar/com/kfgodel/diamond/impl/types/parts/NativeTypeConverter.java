package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.fragments.SuperClassSupplier;
import ar.com.kfgodel.diamond.impl.naming.ClassNames;
import ar.com.kfgodel.diamond.impl.types.*;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleTypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.UpperOnlyTypeBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This type serves as a factory of type instances that know how to map from native to diamond types
 * Created by kfgodel on 21/09/14.
 */
public class NativeTypeConverter {


    /**
     * Creates a type variable instance from an annotated type variable (kind of a mix of type variable and annotations)
     * @param annotatedTypeVariable The annotated mix
     * @return The new type variable instance
     */
    public static TypeVariableInstance convert(AnnotatedTypeVariable annotatedTypeVariable) {
        TypeParts parts = TypeParts.create();
        TypeVariable<?> typeVariable = (TypeVariable<?>) annotatedTypeVariable.getType();
        parts.setTypeName(typeVariable.getTypeName());
        parts.setBounds(UpperOnlyTypeBounds.create(annotatedTypeVariable.getAnnotatedBounds()));
        parts.setAnnotations(annotatedTypeVariable.getAnnotations());
        return TypeVariableInstance.create(parts);
    }

    /**
     * Creates a type variable instance from its native counter part
     * @param typeVariable The native type variable representation
     * @return The new type variable instance
     */
    public static TypeVariableInstance convert(TypeVariable<?> typeVariable) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(typeVariable.getTypeName());
        // For some reason a type variable knows its annotated bounds (unlike other non-annotated types)
        parts.setBounds(UpperOnlyTypeBounds.create(typeVariable.getAnnotatedBounds()));
        parts.setAnnotations(TypeInstanceSupport.NO_ANNOTATIONS);
        return TypeVariableInstance.create(parts);
    }

    /**
     * Creates a class instance representation from a class and its annotations
     * @param nativeClass A native Class
     * @param annotations The class attached annotations
     * @return The created instance
     */
    public static ClassTypeInstance convert(Class<?> nativeClass, Annotation[] annotations) {
        TypeParts parts = TypeParts.create();
        parts.setNames(ClassNames.create(nativeClass));
        parts.setSuperclassSupplier(SuperClassSupplier.create(nativeClass));
        parts.setAnnotations(annotations);
        return ClassTypeInstance.create(parts);
    }

    /**
     * Creates a class instance representation from its native counter-part
     * @param nativeClass The native instance
     * @return The crated class
     */
    public static ClassTypeInstance convert(Class<?> nativeClass) {
        return convert(nativeClass, TypeInstanceSupport.NO_ANNOTATIONS);
    }

    /**
     * Creates a type representation from its native counterpart
     * @param genericArrayType The native representation of the generic array
     * @return The created instance
     */
    public static GenericArrayTypeInstance convert(GenericArrayType genericArrayType) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(genericArrayType.getTypeName());
        parts.setComponentType(Diamond.types().fromUnspecific(genericArrayType.getGenericComponentType()));
        parts.setAnnotations(TypeInstanceSupport.NO_ANNOTATIONS);
        return GenericArrayTypeInstance.create(parts);
    }

    /**
     * Creates a type representation from its native annotated instance
     * @param annotatedArrayType The annotated generic array type
     * @return The created instance
     */
    public static GenericArrayTypeInstance convert(AnnotatedArrayType annotatedArrayType) {
        TypeParts parts = TypeParts.create();
        Type genericArrayType = annotatedArrayType.getType();
        parts.setTypeName(genericArrayType.getTypeName());
        parts.setComponentType(Diamond.types().fromUnspecific(annotatedArrayType.getAnnotatedGenericComponentType()));
        parts.setAnnotations(annotatedArrayType.getAnnotations());
        return GenericArrayTypeInstance.create(parts);
    }

    /**
     * Creates a parameterized type instance from its native annotated representation
     * @param annotatedParameterized The native representation of an annotated type
     * @return The created instance
     */
    public static ParameterizedTypeInstance convert(AnnotatedParameterizedType annotatedParameterized) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(annotatedParameterized.getType().getTypeName());
        List<TypeInstance> typeArguments = Arrays.stream(annotatedParameterized.getAnnotatedActualTypeArguments())
                .map((annotatedType) -> Diamond.types().fromUnspecific(annotatedType))
                .collect(Collectors.toList());
        parts.setTypeArguments(typeArguments);
        parts.setAnnotations(annotatedParameterized.getAnnotations());
        return ParameterizedTypeInstance.create(parts);
    }

    /**
     * Creates a parameterized type from its native representation
     * @param parameterizedType The native representation
     * @return the created instance
     */
    public static ParameterizedTypeInstance convert(ParameterizedType parameterizedType) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(parameterizedType.getTypeName());
        List<TypeInstance> typeArguments = Arrays.stream(parameterizedType.getActualTypeArguments())
                .map((typeArgument)-> Diamond.types().fromUnspecific(typeArgument))
                .collect(Collectors.toList());
        parts.setTypeArguments(typeArguments);
        parts.setAnnotations(TypeInstanceSupport.NO_ANNOTATIONS);
        return ParameterizedTypeInstance.create(parts);
    }

    /**
     * Creates an type wildcard from its annotated native representation
     * @param annotatedWildCard The native mix of wildcard and annotations
     * @return The created instance
     */
    public static TypeWildcardInstance convert(AnnotatedWildcardType annotatedWildCard) {
        WildcardType wildcardType = (WildcardType) annotatedWildCard.getType();
        TypeParts parts = TypeParts.create();
        parts.setTypeName(wildcardType.getTypeName());
        parts.setBounds(DoubleTypeBounds.create(annotatedWildCard.getAnnotatedUpperBounds(), annotatedWildCard.getAnnotatedLowerBounds()));
        parts.setAnnotations(annotatedWildCard.getAnnotations());
        return TypeWildcardInstance.create(parts);
    }

    public static TypeWildcardInstance convert(WildcardType wildcardType) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(wildcardType.getTypeName());
        parts.setBounds(DoubleTypeBounds.create(wildcardType.getUpperBounds(), wildcardType.getLowerBounds()));
        parts.setAnnotations(TypeInstanceSupport.NO_ANNOTATIONS);
        return TypeWildcardInstance.create(parts);
    }

}
