package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

import java.util.function.Function;

/**
 * This type represents a poly invokable adapter arounda function instance
 * * 
 * Created by kfgodel on 02/02/15.
 */
public class PolyFunction implements PolymorphicInvokable {

    private Function function;

    public static PolyFunction create(Function function) {
        PolyFunction polyFunction = new PolyFunction();
        polyFunction.function = function;
        return polyFunction;
    }

    @Override
    public Object invoke(Object... arguments) {
        if(arguments == null){
            throw new IllegalArgumentException("null is not accepted as valid function argument");
        }
        if(arguments.length != 1){
            throw new IllegalArgumentException("Function invokable only accepts 1 argument");
        }
        return this.apply(arguments[0]);
    }

    @Override
    public Object apply(Object argument) {
        return this.function.apply(argument);
    }
}
