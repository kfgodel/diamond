package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.support.UnannotatedFixedTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.GenericArrayComponentTypeSupplier;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents an unannotated native generic array type description
 * Created by kfgodel on 29/09/14.
 */
public class GenericArrayTypeDescription extends UnannotatedFixedTypeDescriptionSupport {

    private GenericArrayType nativeType;

    @Override
    protected Type getNativeType() {
        return nativeType;
    }

    @Override
    public Supplier<Optional<TypeInstance>> getComponentType() {
        return SuppliedValue.lazilyBy(GenericArrayComponentTypeSupplier.create(nativeType));
    }

    public static GenericArrayTypeDescription create(GenericArrayType nativeType) {
        GenericArrayTypeDescription description = new GenericArrayTypeDescription();
        description.nativeType = nativeType;
        return description;
    }

}
