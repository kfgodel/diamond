package ar.com.kfgodel.diamond.impl.lambdas.description;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.lambdas.LambdaDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.SelfSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a description for one of the lambda expressions created from functional interfaces
 * Created by kfgodel on 02/02/15.
 */
public class DefaultLambdaDescription implements LambdaDescription {

  private Supplier<TypeMethod> functionalMethod;
  private PolymorphicInvokable invokable;

  @Override
  public Supplier<PolymorphicInvokable> getPolymorphicFunction() {
    return SelfSupplier.of(invokable);
  }

  @Override
  public Supplier<Nary<ExecutableParameter>> getParameters() {
    return functionalMethod.get()::parameters;
  }

  @Override
  public Supplier<TypeInstance> getReturnType() {
    return functionalMethod.get()::returnType;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getDeclaredExceptions() {
    return Nary::empty;
  }

  public static DefaultLambdaDescription create(PolymorphicInvokable invokableAdapter,
                                                Supplier<TypeMethod> methodSupplier) {
    DefaultLambdaDescription description = new DefaultLambdaDescription();
    description.invokable = invokableAdapter;
    description.functionalMethod = methodSupplier;
    return description;
  }

}
