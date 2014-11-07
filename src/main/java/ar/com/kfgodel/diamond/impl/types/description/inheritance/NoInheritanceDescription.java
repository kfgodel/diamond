package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.NoExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.NoSuperclassSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents the description for a type that has no inheritance
 * Created by kfgodel on 04/11/14.
 */
public class NoInheritanceDescription implements InheritanceDescription {

    public static final NoInheritanceDescription INSTANCE = new NoInheritanceDescription();

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return NoSuperclassSupplier.INSTANCE;
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return NoExtendedTypeSupplier.INSTANCE;
    }

    @Override
    public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
        return NaryFromNative::empty;
    }
}
