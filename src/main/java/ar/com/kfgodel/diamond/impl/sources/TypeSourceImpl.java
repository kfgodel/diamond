package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeSources;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.reflections.NativeClassInstance;
import ar.com.kfgodel.diamond.impl.types.NativeTypeVariable;

import java.lang.reflect.*;

/**
 * This type implements the spurces for type instances
 * Created by kfgodel on 20/09/14.
 */
public class TypeSourceImpl implements TypeSources {

    @Override
    public TypeInstance from(TypeVariable<?> typeVariable) {
        return NativeTypeVariable.create(typeVariable);
    }

    @Override
    public TypeInstance from(ParameterizedType parameterizedType) {
        throw new DiamondException("Not implemented yet");
    }

    @Override
    public TypeInstance from(WildcardType wildcardType) {
        throw new DiamondException("Not implemented yet");
    }

    @Override
    public TypeInstance from(GenericArrayType genericArrayType) {
        throw new DiamondException("Not implemented yet");
    }

    @Override
    public TypeInstance from(Class<?> aClass) {
        return Diamond.of(aClass);
    }

    @Override
    public TypeInstance from(AnnotatedWildcardType annotatedWildCard) {
        throw new DiamondException("Not implemented yet");    }

    @Override
    public TypeInstance from(AnnotatedTypeVariable annotatedTypeVariable) {
        TypeVariable<?> typeVariable = (TypeVariable<?>) annotatedTypeVariable.getType();
        return NativeTypeVariable.create(typeVariable, annotatedTypeVariable.getAnnotations());
    }

    @Override
    public TypeInstance from(AnnotatedParameterizedType annotatedParameterized) {
        throw new DiamondException("Not implemented yet");
    }

    @Override
    public TypeInstance from(AnnotatedArrayType annotatedArrayType) {
        throw new DiamondException("Not implemented yet");    }

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
            return from((AnnotatedParameterizedType)annotatedType);
        }else if (annotatedType instanceof AnnotatedTypeVariable){
            return from((AnnotatedTypeVariable)annotatedType);
        }else if (annotatedType instanceof AnnotatedWildcardType){
            return from((AnnotatedWildcardType)annotatedType);
        } else if (annotatedType instanceof AnnotatedArrayType){
            return from((AnnotatedArrayType)annotatedType);
        }
        Type baseType = annotatedType.getType();
        if(baseType instanceof Class){
            Class<?> aClass = (Class<?>) baseType;
            return NativeClassInstance.create(aClass, annotatedType.getAnnotations());
        }
        throw new DiamondException("An annotated type for something that's not a class doesn't have a creation method: " + annotatedType);
    }

    public static TypeSourceImpl create() {
        TypeSourceImpl source = new TypeSourceImpl();
        return source;
    }

}
