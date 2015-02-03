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
        return FunctionalLambdaDescription.create(PolyRunnable.create(expression), expression, "run");
    }

    public LambdaDescription describe(Consumer expression) {
        return FunctionalLambdaDescription.create(PolyConsumer.create(expression), expression, "accept");
    }

    public LambdaDescription describe(BiConsumer expression) {
        return FunctionalLambdaDescription.create(PolyBiConsumer.create(expression), expression, "accept");
    }

    public LambdaDescription describe(Supplier expression) {
        return FunctionalLambdaDescription.create(PolySupplier.create(expression), expression, "get");
    }

    public LambdaDescription describe(Function expression) {
        return FunctionalLambdaDescription.create(PolyFunction.create(expression), expression, "apply");
    }

    public LambdaDescription describe(BiFunction expression) {
        return FunctionalLambdaDescription.create(PolyBiFunction.create(expression), expression, "apply");
    }

    public LambdaDescription describe(Predicate expression) {
        return FunctionalLambdaDescription.create(PolyPredicate.create(expression), expression, "test");
    }

    public LambdaDescription describe(Invokable expression) {
        return FunctionalLambdaDescription.create(PolyInvokable.create(expression), expression, "invoke");
    }

}