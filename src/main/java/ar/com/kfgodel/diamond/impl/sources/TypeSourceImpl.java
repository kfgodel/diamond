package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeSources;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.NativeTypeConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * This type implements the spurces for type instances
 * Created by kfgodel on 20/09/14.
 */
public class TypeSourceImpl implements TypeSources {

    @Override
    public TypeInstance from(TypeVariable<?> typeVariable) {
        return NativeTypeConverter.convert(typeVariable);
    }

    @Override
    public TypeInstance from(ParameterizedType parameterizedType) {
        return NativeTypeConverter.convert(parameterizedType);
    }

    @Override
    public TypeInstance from(WildcardType wildcardType) {
        return NativeTypeConverter.convert(wildcardType);
    }

    @Override
    public TypeInstance from(GenericArrayType genericArrayType) {
        return NativeTypeConverter.convert(genericArrayType);
    }

    @Override
    public ClassInstance from(Class<?> aClass) {
        return NativeTypeConverter.convert(aClass);
    }

    @Override
    public TypeInstance from(AnnotatedWildcardType annotatedWildCard) {
        return NativeTypeConverter.convert(annotatedWildCard);
    }

    @Override
    public TypeInstance from(AnnotatedTypeVariable annotatedTypeVariable) {
        return NativeTypeConverter.convert(annotatedTypeVariable);
    }

    @Override
    public TypeInstance from(AnnotatedParameterizedType annotatedParameterized) {
        return NativeTypeConverter.convert(annotatedParameterized);
    }

    @Override
    public TypeInstance from(AnnotatedArrayType annotatedArrayType) {
        return NativeTypeConverter.convert(annotatedArrayType);
    }

    @Override
    public TypeInstance from(Class<?> aClass, Annotation[] annotations) {
        return NativeTypeConverter.convert(aClass, annotations);
    }

    @Override
    public TypeInstance fromUnspecific(Type type) {
        if(type instanceof Class){
            return from((Class<?>)type);
        }else if (type instanceof ParameterizedType){
            return from((ParameterizedType)type);
        }else if (type instanceof TypeVariable){
            return from((TypeVariable)type);
        }else if (type instanceof WildcardType){
            return from((WildcardType)type);
        } else if (type instanceof GenericArrayType){
            return from((GenericArrayType)type);
        }
        throw new DiamondException("There's a new type that we cannot represent: " + type);
    }

    @Override
    public TypeInstance fromUnspecific(AnnotatedType annotatedType) {
        if (annotatedType instanceof AnnotatedParameterizedType){
            return from((AnnotatedParameterizedType) annotatedType);
        }else if (annotatedType instanceof AnnotatedTypeVariable){
            return from((AnnotatedTypeVariable)annotatedType);
        }else if (annotatedType instanceof AnnotatedWildcardType){
            return from((AnnotatedWildcardType)annotatedType);
        } else if (annotatedType instanceof AnnotatedArrayType){
            return from((AnnotatedArrayType)annotatedType);
        }
        Type baseType = annotatedType.getType();
        if(baseType instanceof Class){
            return from((Class<?>) baseType, annotatedType.getAnnotations());
        }
        throw new DiamondException("An annotated type for something that's not a class doesn't have a creation method: " + baseType);
    }

    public static TypeSourceImpl create() {
        TypeSourceImpl source = new TypeSourceImpl();
        return source;
    }

}
