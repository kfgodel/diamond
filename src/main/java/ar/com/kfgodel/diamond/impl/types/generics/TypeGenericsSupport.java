package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type serves as a base class for type generics implementations
 * Created by kfgodel on 12/02/15.
 */
public abstract class TypeGenericsSupport implements TypeGenerics {

  private Supplier<Nary<TypeInstance>> runtimeType;

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

  @Override
  public TypeInstance runtimeType() {
    return runtimeType.get()
      .orElseThrow(()-> new DiamondException("Runtime type is not available")); //Better error?
  }

  protected void setRuntimeType(Supplier<Nary<TypeInstance>> runtimeType) {
    this.runtimeType = runtimeType;
  }
}
