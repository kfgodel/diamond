package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ArraysConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.FixedTypeNameSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.SuperClassSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.TypeParametersSupplier;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

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
        return SuppliedValue.lazilyBy(FixedTypeNameSupplier.create(getRawClass(), getNativeType()));
    }

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return SuppliedValue.lazilyBy(SuperClassSupplier.create(getRawClass()));
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return SuppliedValue.lazilyBy(ExtendedTypeSupplier.create(getRawClass(), this.getTypeArguments().get()));
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeParametersSupplier() {
        return TypeParametersSupplier.create(getRawClass());
    }

    @Override
    public Supplier<Stream<TypeConstructor>> getTypeConstructors() {
        Class<?> rawClass = getRawClass();
        if(rawClass.isArray()){
            // Artificial constructor for arrays: https://github.com/kfgodel/diamond/issues/88
            return ArraysConstructorSupplier.create(rawClass);
        }
        return ClassConstructorsSupplier.create(rawClass);
    }


    @Override
    public boolean isForVariableType() {
        return false;
    }



}
