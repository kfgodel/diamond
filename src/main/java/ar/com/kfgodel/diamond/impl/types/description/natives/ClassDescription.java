package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.types.description.support.UnannotatedFixedTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorsSupplier;

import java.lang.reflect.Type;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    @Override
    public Supplier<Stream<TypeConstructor>> getTypeConstructors() {
        return ClassConstructorsSupplier.create(getRawClass());
    }

    public static ClassDescription create(Class<?> nativeType) {
        ClassDescription description = new ClassDescription();
        description.nativeType = nativeType;
        return description;
    }

}
