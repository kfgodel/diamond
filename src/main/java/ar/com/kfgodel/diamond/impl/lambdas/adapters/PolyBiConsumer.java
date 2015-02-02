package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

import java.util.function.BiConsumer;

/**
 * This type represents and poly invokable adapter around a bi-consumer instance
 * Created by kfgodel on 02/02/15.
 */
public class PolyBiConsumer implements PolymorphicInvokable {

    private BiConsumer consumer;
    
    
    
    public static PolyBiConsumer create(BiConsumer biConsumer) {
        PolyBiConsumer polyBiConsumer = new PolyBiConsumer();
        polyBiConsumer.consumer = biConsumer;
        return polyBiConsumer;
    }

    @Override
    public Object invoke(Object... arguments) {
        if(arguments == null){
            throw new IllegalArgumentException("null is not accepted as valid bi-consumer argument");
        }
        if(arguments.length != 2){
            throw new IllegalArgumentException("BiConsumer invokable only accepts 2 arguments");
        }
        this.accept(arguments[0],arguments[1]);
        return null;
    }

    @Override
    public void accept(Object oneConsumed, Object otherConsumed) {
        this.consumer.accept(oneConsumed,otherConsumed);
    }
}
