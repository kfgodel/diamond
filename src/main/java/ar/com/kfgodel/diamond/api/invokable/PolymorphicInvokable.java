package ar.com.kfgodel.diamond.api.invokable;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.util.function.*;

/**
 * This type represents an invokable behavior that can be used with many functional types.<br>
 *     This type can be seen as an adapter between functional interfaces and Invokable.<br>
 *     Only basic and compatible functional types are implemented (i.e.: BiFunction clashes with Function on "andThen" method and its omitted).
 *     Primitive interfaces are omitted for simplicity<br>
 * <br>
 * Default implementation of all functional types rely on Invokable#invoke()
 *
 * Created by kfgodel on 30/10/14.
 */
@FunctionalInterface
public interface PolymorphicInvokable extends Invokable,
        Runnable,
        Consumer<Object>,
        BiConsumer<Object, Object>,
        Supplier<Object>,
        Function<Object, Object>,
        Predicate<Object>,
        UnaryOperator<Object>{

    @Override
    default void run(){
        this.invoke();
    };

    @Override
    default void accept(Object consumedElement){
        this.invoke(consumedElement);
    };

    @Override
    default void accept(Object oneConsumed, Object otherConsumed){
        this.invoke(oneConsumed, otherConsumed);
    };

    @Override
    default Object get(){
        return this.invoke();
    };

    @Override
    default Object apply(Object argument){
        return this.invoke(argument);
    };

    /**
     * BiFunction version. As the BiFunction and Function are not both inheritable, we add the bi-function version
     * of the method but we cannot extend the interface
     * @param oneArg The first argument of teh function
     * @param otherArg The second argument
     * @return The function result
     */
    default Object apply(Object oneArg, Object otherArg){
        return this.invoke(oneArg, otherArg);
    }

    @Override
    default boolean test(Object predicated){
        Object result = this.invoke(predicated);
        if(result instanceof Boolean){
            return (Boolean) result;
        }
        throw new DiamondException("This invokable["+this+"] cannot be used as predicate for argument["+predicated+"] because result["+result+"] is not Boolean");
    };

}
