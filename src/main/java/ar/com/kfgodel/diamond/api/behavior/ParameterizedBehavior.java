package ar.com.kfgodel.diamond.api.behavior;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents a piece of behavior that accepts arguments to parameterize its functioning
 * Created by kfgodel on 15/10/14.
 */
public interface ParameterizedBehavior {

    /**
     * @return The type of parameters accepted by this behavior in the order they are required
     */
    Stream<TypeInstance> parameterTypes();

}
