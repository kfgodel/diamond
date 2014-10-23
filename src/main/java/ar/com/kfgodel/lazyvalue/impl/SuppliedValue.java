package ar.com.kfgodel.lazyvalue.impl;

import ar.com.kfgodel.lazyvalue.api.LazyValue;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a supplier defined value that is delayed until needed
 * Created by kfgodel on 21/09/14.
 */
public class SuppliedValue<T> implements LazyValue<T> {

    private Supplier<T> generatorLambda;
    private T value;

    /**
     * Creates a lazily defined value through a supplier to generate it the first time
     * @param valueGenerator The value generator to be called the first time
     * @param <T> The expect type of value
     * @return The created instance
     */
    public static<T> SuppliedValue<T> lazilyBy(Supplier<T> valueGenerator) {
        SuppliedValue<T> value = new SuppliedValue<>();
        value.generatorLambda = valueGenerator;
        return value;
    }

    /**
     * Creates an eagerly defined value supplier that uses the given value each time it's called
     * @param value The value to use as supplier
     * @param <T> The expected type of value
     * @return The created supplier
     */
    public static<T> LazyValue<T> eagerlyFrom(T value) {
        SuppliedValue<T> supplier = new SuppliedValue<>();
        supplier.value = value;
        return supplier;
    }


    @Override
    public boolean isAlreadyDefined() {
        return generatorLambda == null;
    }

    @Override
    public Optional<Supplier<T>> generator() {
        return Optional.ofNullable(generatorLambda);
    }

    @Override
    public Optional<T> getIfDefined() {
        if(this.isAlreadyDefined()){
            return Optional.of(value);
        }
        return Optional.empty();
    }

    @Override
    public T get() {
        if(!isAlreadyDefined()){
            value = generatorLambda.get();
            generatorLambda = null;
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append("{ value: ");
        if(!this.isAlreadyDefined()){
            builder.append("undefined");
        }else{
            builder.append(value);
        }
        builder.append("}");
        return builder.toString();
    }

}
