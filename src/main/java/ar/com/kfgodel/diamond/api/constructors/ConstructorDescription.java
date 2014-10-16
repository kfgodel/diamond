package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the Diamond description of a constructor abstracting the underlying implementation
 * Created by kfgodel on 15/10/14.
 */
public interface ConstructorDescription {

    /**
     * @return The supplier for the parameter types of the described method
     */
    Supplier<Stream<TypeInstance>> getParameterTypes();

}
