package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.behavior.ParameterizedBehavior;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents the a constructor defined for a type that creates new type instances
 * Created by kfgodel on 15/10/14.
 */
public interface TypeConstructor extends ParameterizedBehavior {

    /**
     * @return The type of parameters accepted by this constructor in the order they are required
     */
    @Override
    Stream<TypeInstance> parameterTypes();
}
