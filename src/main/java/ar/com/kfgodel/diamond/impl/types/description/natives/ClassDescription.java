package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.impl.types.description.support.UnannotatedFixedTypeDescriptionSupport;

import java.lang.reflect.Type;

/**
 * This type represents the description of an unannotated native class
 * Created by kfgodel on 29/09/14.
 */
public class ClassDescription extends UnannotatedFixedTypeDescriptionSupport {

    private Class<?> nativeType;

    @Override
    protected Type getNativeType() {
        return nativeType;
    }

    public static ClassDescription create(Class<?> nativeType) {
        ClassDescription description = new ClassDescription();
        description.nativeType = nativeType;
        return description;
    }

}
