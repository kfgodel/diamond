package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.nary.api.Nary;

import java.util.List;

/**
 * This type represents the bounds of a type that has upper and lower boundaries
 * Created by kfgodel on 20/09/14.
 */
public class DoubleTypeBounds implements TypeBounds {

  private List<TypeInstance> upperBounds;
  private List<TypeInstance> lowerBounds;

  @Override
  public Nary<TypeInstance> upper() {
    return Nary.from(upperBounds.stream());
  }

  @Override
  public Nary<TypeInstance> lower() {
    return Nary.from(lowerBounds.stream());
  }

  public static DoubleTypeBounds create(List<TypeInstance> upper, List<TypeInstance> lower) {
    DoubleTypeBounds bounds = new DoubleTypeBounds();
    bounds.upperBounds = upper;
    bounds.lowerBounds = lower;
    return bounds;
  }

}
