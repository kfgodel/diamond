package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.interfaces.ImmutableInterfacesSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.SuperClassSupplier;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the inheritance description of a fixed type
 * Created by kfgodel on 04/11/14.
 */
public class FixedTypeInheritanceDescription implements InheritanceDescription {

    private Class<?> rawClass;
    private Supplier<Stream<TypeInstance>> typeArguments;

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return SuperClassSupplier.create(rawClass);
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return ExtendedTypeSupplier.create(rawClass, typeArguments.get());
    }

    @Override
    public Supplier<Stream<TypeInstance>> getInterfacesSupplier() {
        return ImmutableInterfacesSupplier.create(rawClass);
    }

    public static FixedTypeInheritanceDescription create(Class<?> rawClass, Supplier<Stream<TypeInstance>> typeArguments) {
        FixedTypeInheritanceDescription description = new FixedTypeInheritanceDescription();
        description.rawClass = rawClass;
        description.typeArguments = typeArguments;
        return description;
    }

}
