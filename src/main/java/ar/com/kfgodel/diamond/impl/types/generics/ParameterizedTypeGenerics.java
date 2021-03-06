package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the generic information of a parameterized or parameterizable type
 * Created by kfgodel on 05/10/14.
 */
public class ParameterizedTypeGenerics extends TypeGenericsSupport {

  private Supplier<? extends Nary<TypeInstance>> typeArguments;
  private Supplier<? extends Nary<TypeInstance>> typeParameters;

  public static ParameterizedTypeGenerics create(Supplier<? extends Nary<TypeInstance>> typeParametersSupplier,
                                                 Supplier<? extends Nary<TypeInstance>> typeArgumentsSupplier) {
    ParameterizedTypeGenerics generics = new ParameterizedTypeGenerics();
    generics.typeParameters = typeParametersSupplier;
    generics.typeArguments = typeArgumentsSupplier;
    return generics;
  }

  @Override
  public Nary<TypeInstance> arguments() {
    return typeArguments.get();
  }

  @Override
  public Nary<TypeInstance> parameters() {
    return this.typeParameters.get();
  }

}
