package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.interfaces.VariableTypeInterfaceSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.optionals.OptionalFromStream;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the inheritance description of a variable type
 * Created by kfgodel on 04/11/14.
 */
public class VariableTypeInheritanceDescription implements InheritanceDescription {


    private Set<Class<?>> upperBoundClasses;
    private Supplier<Stream<TypeInstance>> typeArguments;

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return CachedValue.lazilyBy(() -> Optional.of(Diamond.of(getParentClassFromUpperBounds())));
    }

    /**
     * Tries to get the only class used as upper bound (if any), if none is found, then
     * object is used as parent type
     * @return The type to use as parent of this type variable
     */
    private Class<?> getParentClassFromUpperBounds() {
        // We look for the only allowed class as upper bound
        Optional<Class<?>> optionalClass = OptionalFromStream.using(upperBoundClasses.stream()
                .filter((upper) -> !upper.isInterface()));
        return optionalClass.orElse(Object.class);
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return getSuperclassSupplier();
    }

    @Override
    public Supplier<Stream<TypeInstance>> getInterfacesSupplier() {
        return VariableTypeInterfaceSupplier.create(upperBoundClasses);
    }

    public static VariableTypeInheritanceDescription create(Set<Class<?>> rawClasses, Supplier<Stream<TypeInstance>> typeArguments) {
        VariableTypeInheritanceDescription description = new VariableTypeInheritanceDescription();
        description.upperBoundClasses = rawClasses;
        description.typeArguments = typeArguments;
        return description;
    }

}
