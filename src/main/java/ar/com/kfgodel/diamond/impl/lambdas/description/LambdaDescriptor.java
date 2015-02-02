package ar.com.kfgodel.diamond.impl.lambdas.description;

import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.lambdas.LambdaDescription;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.*;

import java.util.function.*;

/**
 * This type represents a lambda descriptor to get common description for native lambda types
 * Created by kfgodel on 02/02/15.
 */
public class LambdaDescriptor {
    
    public static LambdaDescriptor INSTANCE = new LambdaDescriptor();

    public LambdaDescription describe(Runnable expression) {
        return FunctionalLambdaDescription.create(PolyRunnable.create(expression), Runnable.class, "run");
    }

    public LambdaDescription describe(Consumer expression) {
        return FunctionalLambdaDescription.create(PolyConsumer.create(expression), Consumer.class, "accept");
    }

    public LambdaDescription describe(BiConsumer expression) {
        return FunctionalLambdaDescription.create(PolyBiConsumer.create(expression), BiConsumer.class, "accept");
    }

    public LambdaDescription describe(Supplier expression) {
        return FunctionalLambdaDescription.create(PolySupplier.create(expression), Supplier.class, "get");
    }

    public LambdaDescription describe(Function expression) {
        return FunctionalLambdaDescription.create(PolyFunction.create(expression), Function.class, "apply");
    }

    public LambdaDescription describe(BiFunction expression) {
        return FunctionalLambdaDescription.create(PolyBiFunction.create(expression), BiFunction.class, "apply");
    }

    public LambdaDescription describe(Predicate expression) {
        return FunctionalLambdaDescription.create(PolyPredicate.create(expression), Predicate.class, "test");
    }

    public LambdaDescription describe(Invokable expression) {
        return FunctionalLambdaDescription.create(PolyInvokable.create(expression), Invokable.class, "invoke");
    }

}
