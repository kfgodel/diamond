package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.api.lambdas.LambdaDescription;
import ar.com.kfgodel.diamond.api.sources.LambdaSources;
import ar.com.kfgodel.diamond.impl.lambdas.LambdaInstance;
import ar.com.kfgodel.diamond.impl.lambdas.description.LambdaDescriptor;

import java.util.function.*;

/**
 * Implementation of teh lambda sources
 * Created by kfgodel on 01/02/15.
 */
public class LambdaSourcesImpl implements LambdaSources {

    public static LambdaSourcesImpl create() {
        LambdaSourcesImpl sources = new LambdaSourcesImpl();
        return sources;
    }

    @Override
    public Lambda fromRunnable(Runnable expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromConsumer(Consumer expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromBiConsumer(BiConsumer expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromSupplier(Supplier expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromFunction(Function expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromBiFunction(BiFunction expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromPredicate(Predicate expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromInvokable(Invokable expression) {
        return fromDescription(LambdaDescriptor.INSTANCE.describe(expression));
    }

    @Override
    public Lambda fromDescription(LambdaDescription description) {
        return LambdaInstance.create(description);
    }
}
