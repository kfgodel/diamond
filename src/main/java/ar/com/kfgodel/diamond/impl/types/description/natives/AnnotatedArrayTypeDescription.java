package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.support.AnnotatedTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.ArrayComponentTypeSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents the description of the annotated native generic array type
 * Created by kfgodel on 29/09/14.
 */
public class AnnotatedArrayTypeDescription extends AnnotatedTypeDescriptionSupport {

    private AnnotatedArrayType nativeType;

    @Override
    protected AnnotatedType getAnnotatedType() {
        return nativeType;
    }

    @Override
    protected TypeDescription createUnannotatedDescription() {
        Type type = nativeType.getType();
        if(type instanceof Class){
            // Non generic array
            return ClassDescription.create((Class<?>) type);
        }
        // Generic array
        return GenericArrayTypeDescription.create((GenericArrayType) type);
    }

    @Override
    public Supplier<Nary<TypeInstance>> getComponentType() {
        return ArrayComponentTypeSupplier.create(nativeType);
    }

    public static AnnotatedArrayTypeDescription create(AnnotatedArrayType nativeType) {
        AnnotatedArrayTypeDescription description = new AnnotatedArrayTypeDescription();
        description.nativeType = nativeType;
        return description;
    }

}
