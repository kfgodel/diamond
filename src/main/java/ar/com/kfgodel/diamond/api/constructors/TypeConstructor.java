package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.behavior.ParameterizedBehavior;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the a constructor defined for a type that creates new type instances
 * Created by kfgodel on 15/10/14.
 */
public interface TypeConstructor extends ParameterizedBehavior, TypeMember, Supplier<Object>, Function<Object, Object> {

    /**
     * @return The type of parameters accepted by this constructor in the order they are required
     */
    @Override
    Stream<TypeInstance> parameterTypes();

    /**
     * Invokes the constructor represented by this instance and returns the created object
     * @param arguments The arguments needed to invoke this constructor
     * @return The created object
     */
    Object invoke(Object... arguments);

    /**
     * @return Sames as invoke() without arguments
     */
    @Override
    Object get();

    /**
     * Same as invoke(argument)
     * @param argument
     * @return The created instance
     */
    @Override
    Object apply(Object argument);
}
