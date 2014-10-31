package ar.com.kfgodel.diamond.api.invokable;

/**
 * This type represents a piece of invokable behavior that can act as a function with variable arguments
 * Created by kfgodel on 29/10/14.
 */
public interface Invokable {

    /**
     * Invokes this piece of behavior with the given arguments, and returns the optional return value
     * @param arguments The arguments to use with this behavior
     * @return The  optional return value (null can indicate a no-result)
     */
    Object invoke(Object... arguments);
}
