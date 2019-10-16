package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents an unbounded bounds
 * Created by kfgodel on 21/09/14.
 */
public class NoBounds implements TypeBounds {

  public static final NoBounds INSTANCE = NoBounds.create();

  @Override
  public Nary<TypeInstance> upper() {
    return Nary.empty();
  }

  @Override
  public Nary<TypeInstance> lower() {
    return Nary.empty();
  }

  public static NoBounds create() {
    NoBounds bounds = new NoBounds();
    return bounds;
  }

}
