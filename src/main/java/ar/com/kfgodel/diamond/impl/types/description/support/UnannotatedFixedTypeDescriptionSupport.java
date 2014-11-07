package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ArraysConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.FixedTypeNameSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Optional;
import java.util.function.Supplier;

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
    public InheritanceDescription getInheritanceDescription() {
        return FixedTypeInheritanceDescription.create(getRawClass(), getTypeArguments());
    }

    @Override
    public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
        return GenericTypeParametersSupplier.create(getRawClass());
    }

    @Override
    public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
        Class<?> rawClass = getRawClass();
        if(rawClass.isArray()){
            // Artificial constructor for arrays: https://github.com/kfgodel/diamond/issues/88
            return ArraysConstructorSupplier.create(rawClass);
        }
        return ClassConstructorsSupplier.create(rawClass);
    }

    @Override
    public Supplier<Optional<TypePackage>> getDeclaredPackage() {
        return CachedValue.lazilyBy(() -> Optional.of(Diamond.packages().from(getRawClass().getPackage())));
    }

    @Override
    public boolean isForVariableType() {
        return false;
    }



}
