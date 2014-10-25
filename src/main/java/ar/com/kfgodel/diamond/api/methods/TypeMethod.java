package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.behavior.ParameterizedBehavior;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents a method defined for a type that expresses behavior
 * Created by kfgodel on 18/09/14.
 */
public interface TypeMethod extends Named, ParameterizedBehavior, TypeMember {

    /**
     * @return The method name selector
     */
    @Override
    String name();

    /**
     * @return The type that represents the return type of this method.<br>
     *     The returned instance will be parametrized according to the type this method is declared on
     */
    TypeInstance returnType();

    /**
     * @return The type of parameters accepted by this method in the order they are required
     */
    @Override
    Stream<TypeInstance> parameterTypes();

    /**
     * Invokes the method represented by this instance, in the given object, with the arguments
     * @param instance The object to invoke the method on
     * @param arguments The arguments to used for the invocation
     * @return The result of the invocation
     */
    Object invokeOn(Object instance, Object... arguments);
}
