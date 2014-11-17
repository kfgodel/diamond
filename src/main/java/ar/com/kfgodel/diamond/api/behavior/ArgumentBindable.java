package ar.com.kfgodel.diamond.api.behavior;

import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;

/**
 * This type represents a piece of behavior whose arguments can be bound and made implicit
 * Created by kfgodel on 17/11/14.
 */
public interface ArgumentBindable {

    /**
     * Creates a method call of this behavior with the given arguments
     * @param arguments The arguments to use when invoking this behavior in the method call
     * @return The created method call
     */
    BehaviorCall withArguments(Object... arguments);

}
