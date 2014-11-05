package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ArraysConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.FixedTypeNameSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.packages.TypePackageSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;

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
    public InheritanceDescription getInheritanceDescription() {
        return FixedTypeInheritanceDescription.create(getRawClass(), getTypeArguments());
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeParametersSupplier() {
        return GenericTypeParametersSupplier.create(getRawClass());
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
    public Supplier<Optional<TypePackage>> getDeclaredPackage() {
        return TypePackageSupplier.create(getRawClass());
    }

    @Override
    public boolean isForVariableType() {
        return false;
    }



}
