package ar.com.kfgodel.diamond.api.behavior;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents a piece of behavior that accepts arguments to parameterize its functioning
 * Created by kfgodel on 15/10/14.
 */
public interface ParameterizedBehavior {

    /**
     * @return The type of parameters accepted by this behavior in the order they are required
     */
    Nary<TypeInstance> parameterTypes();

}
