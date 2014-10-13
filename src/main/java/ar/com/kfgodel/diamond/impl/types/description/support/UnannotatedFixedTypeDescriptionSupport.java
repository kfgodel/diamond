package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.FixedTypeNameSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.SuperClassSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.TypeParametersSupplier;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a base class for unannotated fixed types
 * Created by kfgodel on 29/09/14.
 */
public abstract class UnannotatedFixedTypeDescriptionSupport extends UnannotatedTypeDescriptionSupport {

    @Override
    public Supplier<TypeNames> getNames() {
        return FixedTypeNameSupplier.create(getRawClass(), getNativeType());
    }

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return SuperClassSupplier.create(getRawClass());
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return ExtendedTypeSupplier.create(getRawClass(), this.getTypeArguments().get());
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeParametersSupplier() {
        return TypeParametersSupplier.create(getRawClass());
    }

    @Override
    public boolean isForVariableType() {
        return false;
    }



}
