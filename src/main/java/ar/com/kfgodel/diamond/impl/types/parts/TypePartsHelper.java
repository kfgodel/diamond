package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.naming.ClassNames;
import ar.com.kfgodel.diamond.impl.naming.SingleName;
import ar.com.kfgodel.diamond.impl.types.FixedTypeInstance;
import ar.com.kfgodel.diamond.impl.types.VariableTypeInstance;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleTypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.UpperOnlyTypeBounds;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.SuperClassSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.TypeParametersSupplier;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * This type represents a helper that helps completing the parts from different native types
 * Created by kfgodel on 28/09/14.
 */
public class TypePartsHelper {

    private TypeParts parts;
    private Consumer<List<TypeInstance>> actualArgumentsReplacer;

    public static TypePartsHelper create() {
        TypePartsHelper helper = new TypePartsHelper();
        helper.parts = TypeParts.create();
        return helper;
    }

    public void setAnnotationsFrom(AnnotatedType annotatedType) {
        this.setAnnotationsFrom(annotatedType.getAnnotations());
    }

    public void setNamesFrom(Class<?> rawClass, Type type) {
        setNamesFrom(ClassNames.create(rawClass, type.getTypeName()));
    }

    public void setNamesFrom(TypeNames names) {
        parts.setNames(names);
    }

    public void setNamesFrom(Type type) {
        this.setNamesFrom(SingleName.create(type.getTypeName()));
    }

    public void setBoundsFrom(AnnotatedTypeVariable annotatedTypeVariable) {
        setBoundsFrom(annotatedTypeVariable.getAnnotatedBounds());
    }
    public void setBoundsFrom(TypeVariable typeVariable) {
        setBoundsFrom(typeVariable.getAnnotatedBounds());
    }

    public void setBoundsFrom(AnnotatedType[] typeBounds) {
        parts.setBounds(UpperOnlyTypeBounds.create(typeListFrom(typeBounds)));
    }


    public void setBoundsFrom(AnnotatedWildcardType annotatedWildCard) {
        setBoundsFrom(annotatedWildCard.getAnnotatedUpperBounds(), annotatedWildCard.getAnnotatedLowerBounds());
    }
    public void setBoundsFrom(WildcardType wildcardType) {
        setBoundsFrom(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    private void setBoundsFrom(Object[] upperBounds, Object[] lowerBounds) {
        parts.setBounds(DoubleTypeBounds.create(typeListFrom(upperBounds), typeListFrom(lowerBounds)));
    }


    public TypeParts getParts() {
        return parts;
    }

    public void setSuperclassSupplierFrom(Class<?> nativeClass) {
        parts.setSuperclassSupplier(SuperClassSupplier.create(nativeClass));
    }

    public void setTypeArgumentsFrom(List<TypeInstance> typeArguments) {
        if(actualArgumentsReplacer != null){
            // We replace arguments defined in a subtype
            this.actualArgumentsReplacer.accept(typeArguments);
        }
        parts.setTypeArguments(typeArguments);
    }

    public void setNoTypeArguments() {
        this.setTypeArgumentsFrom(Collections.emptyList());
    }

    public void setExtendedTypeSupplierFrom(Class<?> nativeClass) {
        parts.setExtendedTypeSupplier(ExtendedTypeSupplier.create(nativeClass, parts.getTypeArguments()));
    }

    public void setNoComponentType() {
        parts.setComponentType(Optional.empty());
    }

    public void setAnnotationsFrom(Annotation[] annotations) {
        parts.setAnnotations(annotations);
    }

    public void setTypeParametersSupplierFrom(Class<?> nativeClass) {
        parts.setTypeParametersSupplier(TypeParametersSupplier.create(nativeClass));
    }

    public FixedTypeInstance createFixedType() {
        return FixedTypeInstance.create(this.getParts());
    }

    public VariableTypeInstance createVariableType() {
        return VariableTypeInstance.create(this.getParts());
    }

    public void setNoAnnotations() {
        this.setAnnotationsFrom(NoAnnotationsSupplier.NO_ANNOTATIONS);
    }


    public void setComponentTypeFrom(GenericArrayType genericArrayType) {
        setComponentTypeFrom(genericArrayType.getGenericComponentType());
    }

    public void setComponentTypeFrom(AnnotatedArrayType annotatedArrayType) {
        setComponentTypeFrom(annotatedArrayType.getAnnotatedGenericComponentType());
    }

    private void setComponentTypeFrom(Object componentType) {
        parts.setComponentType(Optional.of(Diamond.types().fromUnspecific(componentType)));
    }


    public static List<TypeInstance> typeListFrom(Object[] types) {
        return Arrays.stream(types)
                .map((type) -> Diamond.types().fromUnspecific(type))
                .collect(Collectors.toList());
    }

    public void setActualArgumentsReplacer(Consumer<List<TypeInstance>> actualArgumentsReplacer) {
        this.actualArgumentsReplacer = actualArgumentsReplacer;
    }

    public void setTypeArgumentsFrom(AnnotatedParameterizedType annotatedParameterized) {
        setTypeArgumentsFrom(annotatedParameterized.getAnnotatedActualTypeArguments());
    }

    private void setTypeArgumentsFrom(Object[] actualTypeArguments) {
        List<TypeInstance> typeArguments = Arrays.stream(actualTypeArguments)
                .map((annotatedType) -> Diamond.types().fromUnspecific(annotatedType))
                .collect(Collectors.toList());
        setTypeArgumentsFrom(typeArguments);
    }

    public void setTypeArgumentsFrom(ParameterizedType parameterizedType) {
        setTypeArgumentsFrom(parameterizedType.getActualTypeArguments());
    }
}
