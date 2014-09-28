package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeSources;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.generics.X24;
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
        return NativeTypeConverter.convert(annotatedWildCard, X24.create());
    }

    @Override
    public TypeInstance from(AnnotatedTypeVariable annotatedTypeVariable) {
        return NativeTypeConverter.convert(annotatedTypeVariable, X24.create());
    }

    @Override
    public TypeInstance from(AnnotatedParameterizedType annotatedParameterized) {
        return NativeTypeConverter.convert(annotatedParameterized, X24.create());
    }

    @Override
    public TypeInstance from(AnnotatedArrayType annotatedArrayType) {
        return NativeTypeConverter.convert(annotatedArrayType, X24.create());
    }

    @Override
    public ClassInstance from(Class<?> aClass, Annotation[] annotations) {
        return NativeTypeConverter.convert(aClass, annotations, X24.create());
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
        if(baseType == null){
            throw new DiamondException("The annotated type["+annotatedType+"] has a getType() == null. This is bug on earlier version of the JDK 8.\n" +
                    "Please upgrade your VM of this functionality will not work. Related: https://bugs.openjdk.java.net/browse/JDK-8038994");
        }
        throw new DiamondException("An annotated type for something that's not a class doesn't have a creation method: " + baseType);
    }

    @Override
    public ClassInstance from(X24 x24) {
        AnnotatedType annotatedType = x24.getAnnotatedType();
        if (annotatedType instanceof AnnotatedParameterizedType){
            return NativeTypeConverter.convert((AnnotatedParameterizedType) annotatedType, x24);
        }else if (annotatedType instanceof AnnotatedTypeVariable){
            throw new DiamondException("Is ["+annotatedType.getType()+"] a extended type of a class?");
        }else if (annotatedType instanceof AnnotatedWildcardType){
            throw new DiamondException("Is ["+annotatedType.getType()+"] a extended type of a class?");
        } else if (annotatedType instanceof AnnotatedArrayType){
            return NativeTypeConverter.convert((AnnotatedArrayType)annotatedType, x24);
        }
        Type baseType = annotatedType.getType();
        if(baseType instanceof Class){
            return NativeTypeConverter.convert((Class<?>) baseType, annotatedType.getAnnotations(), x24);
        }
        if(baseType == null){
            throw new DiamondException("The annotated type["+annotatedType+"] has a getType() == null. This is bug on earlier version of the JDK 8.\n" +
                    "Please upgrade your VM of this functionality will not work. Related: https://bugs.openjdk.java.net/browse/JDK-8038994");
        }
        throw new DiamondException("An annotated type for something that's not a class doesn't have a creation method: " + baseType);
    }

    public static TypeSourceImpl create() {
        TypeSourceImpl source = new TypeSourceImpl();
        return source;
    }

    @Override
    public TypeInstance fromUnspecific(Object type) throws DiamondException {
        if(type instanceof TypeInstance){
            return (TypeInstance) type;
        }else if(type instanceof AnnotatedType){
            return fromUnspecific((AnnotatedType) type);
        }else if (type instanceof Type){
            return fromUnspecific((Type)type);
        }
        throw new DiamondException("The object doesn't represent a type: "+ type);
    }
}
