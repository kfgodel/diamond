package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the description of a diamond method to get a {@link TypeMethod}
 * Created by kfgodel on 07/10/14.
 */
public interface MethodDescription {

    /**
     * @return The supplier for getting the method name
     */
    Supplier<String> getName();

    /**
     * @return The supplier for the return type of the described method
     */
    Supplier<TypeInstance> getReturnType();

    /**
     * @return The supplier for the parameter types of the described method
     */
    Supplier<Stream<TypeInstance>> getParameterTypes();
}
