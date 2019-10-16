package ar.com.kfgodel.diamond.impl.lambdas.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.lambdas.LambdaDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a description for one of the lambda expressions created from functional interfaces
 * Created by kfgodel on 02/02/15.
 */
public class FunctionalLambdaDescription implements LambdaDescription {

  private Supplier<TypeMethod> functionalMethod;
  private PolymorphicInvokable invokable;

  @Override
  public Supplier<PolymorphicInvokable> getPolymorphicFunction() {
    return CachedValue.eagerlyFrom(invokable);
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

  public static FunctionalLambdaDescription create(PolymorphicInvokable invokableAdapter, Object functionalInstance, String functionalMethodName) {
    FunctionalLambdaDescription description = new FunctionalLambdaDescription();
    description.invokable = invokableAdapter;
    description.functionalMethod = CachedValue.lazilyBy(() -> Diamond.of(functionalInstance.getClass()).methods().named(functionalMethodName).get());
    return description;
  }

}
