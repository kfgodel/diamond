package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.interfaces.ImmutableInterfacesSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.SuperClassSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the inheritance description of a fixed type
 * Created by kfgodel on 04/11/14.
 */
public class FixedTypeInheritanceDescription implements InheritanceDescription {

    private Class<?> rawClass;
    private Supplier<Nary<TypeInstance>> typeArguments;

    @Override
    public Supplier<Nary<TypeInstance>> getSuperclassSupplier() {
        return SuperClassSupplier.create(rawClass);
    }

    @Override
    public Supplier<Nary<TypeInstance>> getExtendedTypeSupplier() {
        return ExtendedTypeSupplier.create(rawClass, typeArguments.get());
    }

    @Override
    public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
        return ImmutableInterfacesSupplier.create(rawClass);
    }

    public static FixedTypeInheritanceDescription create(Class<?> rawClass, Supplier<Nary<TypeInstance>> typeArguments) {
        FixedTypeInheritanceDescription description = new FixedTypeInheritanceDescription();
        description.rawClass = rawClass;
        description.typeArguments = typeArguments;
        return description;
    }

}
