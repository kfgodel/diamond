package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.TypeVariable;

/**
 * This type represents a type variable
 * Created by kfgodel on 20/09/14.
 */
public class NativeTypeVariable extends TypeInstanceSupport {

    private TypeVariable<?> nativeVariable;

    @Override
    public String name() {
        return nativeVariable.getName();
    }

    @Override
    public TypeBounds bounds() {
        return NativeTypeBounds.create(nativeVariable.getAnnotatedBounds(), null);
    }

    public static NativeTypeVariable create(TypeVariable<?> variable, Annotation[] annotations) {
        NativeTypeVariable instance = new NativeTypeVariable();
        instance.nativeVariable = variable;
        instance.setAnnotations(annotations);
        return instance;
    }

    public static NativeTypeVariable create(TypeVariable<?> variable) {
        return create(variable, NO_ANNOTATIONS);
    }


}
