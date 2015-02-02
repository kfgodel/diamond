package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

import java.util.function.BiFunction;

/**
 * This type represents a poly invokable adapter around a bifunction instance 
 * Created by kfgodel on 02/02/15.
 */
public class PolyBiFunction implements PolymorphicInvokable {
    
    private BiFunction function;
    
    @Override
    public Object invoke(Object... arguments) {
        if(arguments == null){
            throw new IllegalArgumentException("null is not accepted as valid bi-function argument");
        }
        if(arguments.length != 2){
            throw new IllegalArgumentException("BiFunction invokable only accepts 2 arguments");
        }
        return this.apply(arguments[0], arguments[1]);
    }

    @Override
    public Object apply(Object oneArg, Object otherArg) {
        return function.apply(oneArg, otherArg);
    }

    public static PolyBiFunction create(BiFunction biFunction) {
        PolyBiFunction polyBiFunction = new PolyBiFunction();
        polyBiFunction.function = biFunction;
        return polyBiFunction;
    }

}
