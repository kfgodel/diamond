package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.api.lambdas.LambdaDescription;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This type represents the different source to get a lambda reification
 * *
 * Created by kfgodel on 01/02/15.
 */
public interface LambdaSources {

  /**
   * Creates a diamond representation of a runnable lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromRunnable(Runnable expression);

  /**
   * Creates a diamond representation of a consumer lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromConsumer(Consumer expression);

  /**
   * Creates a diamond representation of a bi-arg-consumer lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromBiConsumer(BiConsumer expression);


  /**
   * Creates a diamond representation of a supplier lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromSupplier(Supplier expression);

  /**
   * Creates a diamond representation of a function lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromFunction(Function expression);

  /**
   * Creates a diamond representation of a bi-arg-function lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromBiFunction(BiFunction expression);

  /**
   * Creates a diamond representation of a predicate lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromPredicate(Predicate expression);

  /**
   * Creates a diamond representation of a nary-arg-function lambda
   *
   * @param expression The lambda expression or instance
   * @return The diamond representation of the lambda expression
   */
  Lambda fromInvokable(Invokable expression);

  /**
   * Creates a lambda reification of a lambda expresion using its description
   *
   * @param description The diamond description of a lambda expression
   * @return
   */
  Lambda fromDescription(LambdaDescription description);
}
