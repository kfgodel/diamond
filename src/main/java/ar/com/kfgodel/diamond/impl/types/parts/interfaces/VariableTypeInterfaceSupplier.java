package ar.com.kfgodel.diamond.impl.types.parts.interfaces;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the supplier of a variable type interfaces
 * Created by kfgodel on 04/11/14.
 */
public class VariableTypeInterfaceSupplier {

    public static Supplier<Stream<TypeInstance>> create(Set<Class<?>> rawClasses) {
        return StreamFromCollectionSupplier.lazilyBy(() -> rawClasses.stream()
                                                .filter(Class::isInterface)
                                                .map(Diamond::of)
                                                .collect(Collectors.toList())
        );
    }
}
