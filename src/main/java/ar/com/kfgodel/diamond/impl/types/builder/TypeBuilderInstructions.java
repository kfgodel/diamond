package ar.com.kfgodel.diamond.impl.types.builder;

import ar.com.kfgodel.diamond.impl.generics.X24;
import ar.com.kfgodel.diamond.impl.types.VariableTypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.TypePartsHelper;

import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * This type represents the knowledge to build a type instance from each native type
 * Created by kfgodel on 28/09/14.
 */
public class TypeBuilderInstructions {

    public static TypeBuilder<VariableTypeInstance> getFor(TypeVariable<?> typeVariable) {
        TypeBuilder<VariableTypeInstance> typeBuilder = TypeBuilder.create();
        typeBuilder.forAnnotations(TypePartsHelper::setNoAnnotations);
        typeBuilder.forNames((helper) -> helper.setNamesFrom(typeVariable));
        typeBuilder.forBounds((helper) -> helper.setBoundsFrom(typeVariable));
        return typeBuilder;
    }

    public static TypeBuilder<VariableTypeInstance> getFor(WildcardType wildcardType) {
        TypeBuilder<VariableTypeInstance> typeBuilder = TypeBuilder.create();
        typeBuilder.forAnnotations(TypePartsHelper::setNoAnnotations);
        typeBuilder.forNames((helper) -> helper.setNamesFrom(wildcardType));
        typeBuilder.forBounds((helper) -> helper.setBoundsFrom(wildcardType));
        return typeBuilder;
    }

    public static TypeBuilder<VariableTypeInstance> getFor(AnnotatedTypeVariable annotatedTypeVariable) {
        TypeVariable<?> typeVariable = (TypeVariable<?>) annotatedTypeVariable.getType();
        TypeBuilder<VariableTypeInstance> builder = getFor(typeVariable);
        builder.forAnnotations((helper)-> helper.setAnnotationsFrom(annotatedTypeVariable));
        builder.forBounds((helper)-> helper.setBoundsFrom(annotatedTypeVariable));
        return builder;
    }

    public static TypeBuilder<VariableTypeInstance> getFor(AnnotatedWildcardType annotatedWildCard, X24 x24) {
        WildcardType wildcardType = (WildcardType) annotatedWildCard.getType();
        TypeBuilder<VariableTypeInstance> builder = getFor(wildcardType);
        builder.forAnnotations((helper)-> helper.setAnnotationsFrom(annotatedWildCard));
        builder.forBounds((helper) -> helper.setBoundsFrom(annotatedWildCard));
        return builder;
    }

}
