package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.VariableTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.names.VariableTypeNamesSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type serves as a base class for descriptions of types that represent variable types (type variables and wildcards)
 * Created by kfgodel on 28/09/14.
 */
public abstract class UnannotatedVariableTypeDescriptionSupport extends UnannotatedTypeDescriptionSupport {

    @Override
    public Supplier<TypeNames> getNames() {
        return VariableTypeNamesSupplier.create(getNativeType());
    }

    @Override
    public boolean isForVariableType() {
        return true;
    }

    @Override
    public Supplier<Nary<TypePackage>> getDeclaredPackage() {
        return Nary::empty;
    }

    @Override
    public InheritanceDescription getInheritanceDescription() {
        return VariableTypeInheritanceDescription.create(getBehavioralClasses(), getTypeArguments());
    }
}
