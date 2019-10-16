package ar.com.kfgodel.diamond.api.lambdas;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the diamond description of a lambda expression
 * <p>
 * Created by kfgodel on 01/02/15.
 */
public interface LambdaDescription {

  /**
   * @return The function that can be invoked to execute the lambda code
   */
  Supplier<PolymorphicInvokable> getPolymorphicFunction();

  /**
   * @return The set of parameters needed for this lambda invocation
   */
  Supplier<Nary<ExecutableParameter>> getParameters();

  /**
   * @return The expected return type declared in this lambda
   */
  Supplier<TypeInstance> getReturnType();

  /**
   * @return The set of declared exception (usually none, as lambda syntax doesn't include exceptions)
   */
  Supplier<Nary<TypeInstance>> getDeclaredExceptions();

}
