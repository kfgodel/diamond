package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.impl.fragments.RawClassExtractor;
import ar.com.kfgodel.diamond.impl.generics.X24;
import ar.com.kfgodel.diamond.impl.types.FixedTypeInstance;
import ar.com.kfgodel.diamond.impl.types.VariableTypeInstance;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

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
    public static VariableTypeInstance convert(AnnotatedTypeVariable annotatedTypeVariable, X24 x24) {
        TypeVariable<?> typeVariable = (TypeVariable<?>) annotatedTypeVariable.getType();
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setAnnotationsFrom(annotatedTypeVariable);
        helper.setNamesFrom(typeVariable);
        helper.setBoundsFrom(annotatedTypeVariable);
        return helper.createVariableType();
    }

    /**
     * Creates a type variable instance from its native counter part
     * @param typeVariable The native type variable representation
     * @return The new type variable instance
     */
    public static VariableTypeInstance convert(TypeVariable<?> typeVariable) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setNoAnnotations();
        helper.setNamesFrom(typeVariable);
        helper.setBoundsFrom(typeVariable);
        return helper.createVariableType();
    }

    /**
     * Creates an type wildcard from its annotated native representation
     * @param annotatedWildCard The native mix of wildcard and annotations
     * @return The created instance
     */
    public static VariableTypeInstance convert(AnnotatedWildcardType annotatedWildCard, X24 x24) {
        WildcardType wildcardType = (WildcardType) annotatedWildCard.getType();
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setAnnotationsFrom(annotatedWildCard);
        helper.setNamesFrom(wildcardType);
        helper.setBoundsFrom(annotatedWildCard);
        return helper.createVariableType();
    }

    public static VariableTypeInstance convert(WildcardType wildcardType) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setNoAnnotations();
        helper.setNamesFrom(wildcardType);
        helper.setBoundsFrom(wildcardType);
        return helper.createVariableType();
    }

    /**
     * Creates a class instance representation from a class and its annotations
     * @param nativeClass A native Class
     * @param annotations The class attached annotations
     * @return The created instance
     */
    public static FixedTypeInstance convert(Class<?> nativeClass, Annotation[] annotations, X24 x24) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setNamesFrom(nativeClass);
        helper.setSuperclassSupplierFrom(nativeClass);
        helper.setNoTypeArguments();
        helper.setExtendedTypeSupplierFrom(nativeClass);
        helper.setNoComponentType();
        helper.setAnnotationsFrom(annotations);
        helper.setTypeParametersSupplierFrom(nativeClass);
        return helper.createFixedType();
    }

    /**
     * Creates a class instance representation from its native counter-part
     * @param nativeClass The native instance
     * @return The crated class
     */
    public static FixedTypeInstance convert(Class<?> nativeClass) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setNamesFrom(nativeClass);
        helper.setSuperclassSupplierFrom(nativeClass);
        helper.setNoTypeArguments();
        helper.setExtendedTypeSupplierFrom(nativeClass);
        helper.setNoComponentType();
        helper.setNoAnnotations();
        helper.setTypeParametersSupplierFrom(nativeClass);
        return helper.createFixedType();
    }

    /**
     * Creates a type representation from its native counterpart
     * @param genericArrayType The native representation of the generic array
     * @return The created instance
     */
    public static FixedTypeInstance convert(GenericArrayType genericArrayType) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setNoAnnotations();

        Class<?> rawClass = RawClassExtractor.from(genericArrayType);
        helper.setNamesFrom(rawClass, genericArrayType);
        helper.setSuperclassSupplierFrom(rawClass);
        helper.setNoTypeArguments();
        helper.setExtendedTypeSupplierFrom(rawClass);
        helper.setTypeParametersSupplierFrom(rawClass);
        helper.setComponentTypeFrom(genericArrayType);

        return helper.createFixedType();
    }

    /**
     * Creates a type representation from its native annotated instance
     * @param annotatedArrayType The annotated generic array type
     * @return The created instance
     */
    public static FixedTypeInstance convert(AnnotatedArrayType annotatedArrayType, X24 x24) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setAnnotationsFrom(annotatedArrayType);
        helper.setComponentTypeFrom(annotatedArrayType);

        GenericArrayType genericArrayType = (GenericArrayType)annotatedArrayType.getType();

        Class<?> rawClass = RawClassExtractor.from(genericArrayType);
        helper.setNamesFrom(rawClass, genericArrayType);
        helper.setSuperclassSupplierFrom(rawClass);
        helper.setNoTypeArguments();
        helper.setExtendedTypeSupplierFrom(rawClass);
        helper.setTypeParametersSupplierFrom(rawClass);

        return helper.createFixedType();
    }

    /**
     * Creates a parameterized type instance from its native annotated representation
     * @param annotatedParameterized The native representation of an annotated type
     * @param x24
     * @return The created instance
     */
    public static FixedTypeInstance convert(AnnotatedParameterizedType annotatedParameterized, X24 x24) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setAnnotationsFrom(annotatedParameterized);

        ParameterizedType parameterizedType = (ParameterizedType) annotatedParameterized.getType();
        Class<?> rawClass = RawClassExtractor.from(parameterizedType);
        helper.setNamesFrom(rawClass, parameterizedType);
        helper.setSuperclassSupplierFrom(rawClass);
        helper.setNoComponentType();
        helper.setTypeParametersSupplierFrom(rawClass);

        helper.setActualArgumentsReplacer(x24.getActualArgumentsReplacer());
        helper.setTypeArgumentsFrom(annotatedParameterized);
        helper.setExtendedTypeSupplierFrom(rawClass);
        return helper.createFixedType();
    }

    /**
     * Creates a parameterized type from its native representation
     * @param parameterizedType The native representation
     * @return the created instance
     */
    public static FixedTypeInstance convert(ParameterizedType parameterizedType) {
        TypePartsHelper helper = TypePartsHelper.create();
        helper.setNoAnnotations();

        Class<?> rawClass = RawClassExtractor.from(parameterizedType);
        helper.setNamesFrom(rawClass, parameterizedType);
        helper.setSuperclassSupplierFrom(rawClass);
        helper.setNoComponentType();
        helper.setTypeParametersSupplierFrom(rawClass);

        helper.setTypeArgumentsFrom(parameterizedType);

        helper.setExtendedTypeSupplierFrom(rawClass);
        return helper.createFixedType();
    }


}
