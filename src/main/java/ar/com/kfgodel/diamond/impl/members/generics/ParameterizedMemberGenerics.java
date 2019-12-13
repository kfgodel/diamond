package ar.com.kfgodel.diamond.impl.members.generics;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the generics information of a parameterized type member
 * Created by kfgodel on 01/11/14.
 */
public class ParameterizedMemberGenerics implements Generics {

  private Supplier<Nary<TypeInstance>> typeParameters;

  @Override
  public Nary<TypeInstance> parameters() {
    return typeParameters.get();
  }

  public static ParameterizedMemberGenerics create(Nary<TypeInstance> typeParameters) {
    ParameterizedMemberGenerics generics = new ParameterizedMemberGenerics();
    generics.typeParameters = CachedValues.from(typeParameters); // Consume and cache when accessed
    return generics;
  }

}
