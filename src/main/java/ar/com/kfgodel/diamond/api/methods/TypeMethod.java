package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.behavior.ParameterizedBehavior;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a method defined for a type that expresses behavior
 * Created by kfgodel on 18/09/14.
 */
public interface TypeMethod extends Named, ParameterizedBehavior, TypeMember, Runnable, Consumer<Object>, BiConsumer<Object, Object>, Supplier<Object>, Function<Object, Object>, Invokable {

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

    /**
     * Invokes this method as a static method with no args (and no interesting return value),
     * Calling invoke on it without an instance
     */
    @Override
    void run();

    /**
     * Invokes this method as a static method with no args a return value
     * @return The method returned value
     */
    @Override
    Object get();

    /**
     * Invokes this method with different semantics depending on whether it's instance or static method:
     * - Static: Invokes the method with the given argument
     * - Instance: Invokes as no arg over the given instance
     * @param argumentOrInstance The argument for a static method, or instance to invoke on for instance method
     */
    @Override
    void accept(Object argumentOrInstance);

    /**
     * Invokes this method with different semantics depending on whether it's instance or static method:
     * - Static: Invokes the method with the given argument
     * - Instance: Invokes as no arg over the given instance
     * @param argumentOrInstance The argument for a static method, or instance to invoke on for instance method
     * @return The returned method value
     */
    @Override
    Object apply(Object argumentOrInstance);

    /**
     * Invokes this method with different semantics depending on whether it's instance or static method:
     * - Static: Invokes the method with the given arguments
     * - Instance: Invokes as one arg method over the given instance
     * @param argumentOrInstance The first argument for a static method, or instance to invoke on for instance method
     * @param extraArgument The second argument for a static method, or first argument for instance method
     */
    @Override
    void accept(Object argumentOrInstance, Object extraArgument);
}
