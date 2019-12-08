package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type serves as a base class for type generics implementations
 * Created by kfgodel on 12/02/15.
 */
public abstract class TypeGenericsSupport implements TypeGenerics {

  @Override
  public TypeBounds bounds() {
    return NoBounds.INSTANCE;
  }

  @Override
  public Nary<TypeInstance> arguments() {
    return Nary.empty();
  }

  @Override
  public Nary<TypeInstance> parameters() {
    return Nary.empty();
  }

}
