package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import java.util.function.Consumer;

/**
 * This type is a poly invokable adapter around a consumer instance
 * Created by kfgodel on 02/02/15.
 */
public class PolyConsumer extends PolyAdapterSupport {
    
    private Consumer consumer;

    @Override
    public Object invoke(Object... arguments) {
        if(arguments == null){
            throw new IllegalArgumentException("null is not accepted as valid consumer argument");
        }
        if(arguments.length != 1){
            throw new IllegalArgumentException("Consumer invokable only accepts 1 argument");
        }
        this.accept(arguments[0]);
        return null;
    }

    @Override
    public void accept(Object consumedElement) {
        this.consumer.accept(consumedElement);
    }

    public static PolyConsumer create(Consumer consumer) {
        PolyConsumer polyConsumer = new PolyConsumer();
        polyConsumer.consumer = consumer;
        return polyConsumer;
    }

    @Override
    public Object adaptedCode() {
        return consumer;
    }
}
