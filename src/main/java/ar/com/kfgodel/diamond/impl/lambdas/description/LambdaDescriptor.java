package ar.com.kfgodel.diamond.impl.lambdas.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.lambdas.LambdaDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolyBiConsumer;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolyBiFunction;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolyConsumer;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolyFunction;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolyInvokable;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolyPredicate;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolyRunnable;
import ar.com.kfgodel.diamond.impl.lambdas.adapters.PolySupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This type represents a lambda descriptor to get common description for native lambda types
 * Created by kfgodel on 02/02/15.
 */
public class LambdaDescriptor {

  public static LambdaDescriptor INSTANCE = new LambdaDescriptor();

  public LambdaDescription describe(Runnable lambda) {
    return DefaultLambdaDescription.create(PolyRunnable.create(lambda), supplyMethodOf(lambda, "run"));
  }

  public LambdaDescription describe(Consumer lambda) {
    return DefaultLambdaDescription.create(PolyConsumer.create(lambda), supplyMethodOf(lambda, "accept"));
  }

  public LambdaDescription describe(BiConsumer lambda) {
    return DefaultLambdaDescription.create(PolyBiConsumer.create(lambda), supplyMethodOf(lambda, "accept"));
  }

  public LambdaDescription describe(Supplier lambda) {
    return DefaultLambdaDescription.create(PolySupplier.create(lambda), supplyMethodOf(lambda, "get"));
  }

  public LambdaDescription describe(Function lambda) {
    return DefaultLambdaDescription.create(PolyFunction.create(lambda), supplyMethodOf(lambda, "apply"));
  }

  public LambdaDescription describe(BiFunction lambda) {
    return DefaultLambdaDescription.create(PolyBiFunction.create(lambda), supplyMethodOf(lambda, "apply"));
  }

  public LambdaDescription describe(Predicate lambda) {
    return DefaultLambdaDescription.create(PolyPredicate.create(lambda), supplyMethodOf(lambda, "test"));
  }

  public LambdaDescription describe(Invokable expression) {
    return DefaultLambdaDescription.create(PolyInvokable.create(expression),
      supplyMethodOf(expression, "invoke"));
  }

  private Supplier<TypeMethod> supplyMethodOf(Object instance, String methodName) {
    return CachedValue.from(()->{
      return Diamond.of(instance.getClass())
        .methods()
        .named(methodName)
        .asUni().get();
    });
  }

}
