package ar.com.kfgodel.diamond.impl.types.generics.parameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.parametrization.SupertypeParametrization;

import java.util.List;
import java.util.function.Consumer;

/**
 * This type represents the argument replacer that from a subtype arguments and a supertype parametrization
 * knows how to replace the supertype arguments for their actual values
 * Created by kfgodel on 27/09/14.
 */
public class ActualArgumentReplacer implements Consumer<List<TypeInstance>> {
  private List<TypeInstance> subtypeArguments;
  private SupertypeParametrization parametrization;

  @Override
  public void accept(List<TypeInstance> supertypeArgs) {
    parametrization.parameterizeWith(subtypeArguments, supertypeArgs);
  }

  public static ActualArgumentReplacer create(List<TypeInstance> subtypeArguments, SupertypeParametrization parametrization) {
    ActualArgumentReplacer replacer = new ActualArgumentReplacer();
    replacer.subtypeArguments = subtypeArguments;
    replacer.parametrization = parametrization;
    return replacer;
  }

}
