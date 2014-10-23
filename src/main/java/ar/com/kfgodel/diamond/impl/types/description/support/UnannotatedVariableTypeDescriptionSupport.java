package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.impl.types.parts.names.VariableTypeNamesSupplier;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.function.Supplier;

/**
 * This type serves as a base class for descriptions of types that represent variable types (type variables and wildcards)
 * Created by kfgodel on 28/09/14.
 */
public abstract class UnannotatedVariableTypeDescriptionSupport extends UnannotatedTypeDescriptionSupport {

    @Override
    public Supplier<TypeNames> getNames() {
        return SuppliedValue.lazilyBy(VariableTypeNamesSupplier.create(getNativeType()));
    }

    @Override
    public boolean isForVariableType() {
        return true;
    }

}
