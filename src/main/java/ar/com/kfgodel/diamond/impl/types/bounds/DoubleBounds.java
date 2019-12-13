package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the bounds of a type that has upper and lower boundaries
 * Created by kfgodel on 20/09/14.
 */
public class DoubleBounds implements TypeBounds {

  private Supplier<Nary<TypeInstance>> upperBounds;
  private Supplier<Nary<TypeInstance>> lowerBounds;

  @Override
  public Nary<TypeInstance> upper() {
    return upperBounds.get();
  }

  @Override
  public Nary<TypeInstance> lower() {
    return lowerBounds.get();
  }

  public static DoubleBounds create(Nary<TypeInstance> upper, Nary<TypeInstance> lower) {
    DoubleBounds bounds = new DoubleBounds();
    bounds.upperBounds = CachedValues.from(upper); // Consume and cache when accessed
    bounds.lowerBounds = CachedValues.from(lower);
    return bounds;
  }

}
