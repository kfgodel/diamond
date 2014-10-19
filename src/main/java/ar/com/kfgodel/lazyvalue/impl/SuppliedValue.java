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

    public static<T> SuppliedValue<T> from(Supplier<T> valueGenerator) {
        SuppliedValue<T> value = new SuppliedValue<>();
        value.generatorLambda = valueGenerator;
        return value;
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
