package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.invokable.Invokable;

/**
 * This type represents a piece of behavior defined in a type that can be invoked (statically or with an instance)
 * Created by kfgodel on 17/11/14.
 */
public interface TypeBehavior extends Invokable {

    /**
     * Invokes the behavior represented by this instance, on the given instance, with the arguments
     * @param instance The object to invoke the behavior on
     * @param arguments The arguments to be used for the invocation
     * @return The result of the invocation
     */
    Object invokeOn(Object instance, Object... arguments);

}
