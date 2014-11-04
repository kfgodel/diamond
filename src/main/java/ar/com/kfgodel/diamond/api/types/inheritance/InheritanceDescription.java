package ar.com.kfgodel.diamond.api.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the inheritance description of a type
 * Created by kfgodel on 04/11/14.
 */
public interface InheritanceDescription {

    /**
     * @return The supplier of superclass for this type
     */
    Supplier<Optional<TypeInstance>> getSuperclassSupplier();

    /**
     * @return The supplier of extended types
     */
    Supplier<Optional<TypeInstance>> getExtendedTypeSupplier();

    /**
     * @return The supplier of the type implemented interfaces
     */
    Supplier<Stream<TypeInstance>> getInterfacesSupplier();

}
