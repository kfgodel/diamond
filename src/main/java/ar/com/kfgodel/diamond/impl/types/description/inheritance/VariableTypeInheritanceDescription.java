package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.interfaces.VariableTypeInterfaceSupplier;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the inheritance description of a variable type
 * Created by kfgodel on 04/11/14.
 */
public class VariableTypeInheritanceDescription implements InheritanceDescription {


    private Set<Class<?>> rawClasses;

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return NoInheritanceDescription.INSTANCE.getSuperclassSupplier();
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return NoInheritanceDescription.INSTANCE.getExtendedTypeSupplier();
    }

    @Override
    public Supplier<Stream<TypeInstance>> getInterfacesSupplier() {
        return VariableTypeInterfaceSupplier.create(rawClasses);
    }

    public static VariableTypeInheritanceDescription create(Set<Class<?>> rawClasses) {
        VariableTypeInheritanceDescription description = new VariableTypeInheritanceDescription();
        description.rawClasses = rawClasses;
        return description;
    }

}
