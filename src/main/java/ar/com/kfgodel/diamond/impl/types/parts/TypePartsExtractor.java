package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.fragments.RawClassExtractor;
import ar.com.kfgodel.diamond.impl.naming.ClassNames;
import ar.com.kfgodel.diamond.impl.naming.SingleName;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * This type represents a part extractor that will extrac the parts
 * Created by kfgodel on 28/09/14.
 */
public class TypePartsExtractor {

    private AnnotatedType annotatedType;
    private Type type;
    private Object nativeType;
    private TypeParts parts;
    private Class<?> rawClass;


    public TypeParts extract() {
        defineAnnotations();
        defineNames();
        if(typeIsVariable()){
            defineBounds();
        }else{

        }
        return getParts();
    }

    private void defineBounds() {
        TypeBounds bounds;
        if(getType() instanceof TypeVariable){
            bounds =
        }else {

        }
        getParts().setBounds(bounds);
    }

    private void defineNames() {
        TypeNames names;
        if (typeIsVariable()) {
            names = SingleName.create(getType().getTypeName());
        } else {
            names = ClassNames.create(getRawClass(), type.getTypeName());
        }
        getParts().setNames(names);
    }

    private boolean typeIsVariable() {
        return getType() instanceof TypeVariable || getType() instanceof WildcardType;
    }

    private void defineAnnotations() {
        if(nativeType instanceof AnnotatedType){
            getParts().setAnnotations(getAnnotatedType().getAnnotations());
        }else{
            getParts().setAnnotations(NoAnnotationsSupplier.NO_ANNOTATIONS);
        }
    }


    public static TypePartsExtractor create(Object nativeType) {
        TypePartsExtractor extractor = new TypePartsExtractor();
        extractor.nativeType = nativeType;
        return extractor;
    }

    public TypeParts getParts() {
        if (parts == null) {
            parts = TypeParts.create();
        }
        return parts;
    }

    public AnnotatedType getAnnotatedType() {
        if (annotatedType == null) {
            annotatedType = (AnnotatedType) nativeType;
        }
        return annotatedType;
    }

    public Type getType() {
        if (type == null) {
            if(nativeType instanceof AnnotatedType){
                type = getAnnotatedType().getType();
            }else{
                type = (Type) nativeType;
            }
        }
        return type;
    }

    public Class<?> getRawClass() {
        if (rawClass == null) {
            rawClass =  RawClassExtractor.fromUnspecific(getType());
        }
        return rawClass;
    }
}
