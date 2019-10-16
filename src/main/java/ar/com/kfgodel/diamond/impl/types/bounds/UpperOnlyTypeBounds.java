package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.nary.api.Nary;

import java.util.List;

/**
 * This type represents a type boundary with only upper bounds (common for type variables)
 * Created by kfgodel on 20/09/14.
 */
public class UpperOnlyTypeBounds implements TypeBounds {

  private List<TypeInstance> upperBounds;

  @Override
  public Nary<TypeInstance> upper() {
    return Nary.create(upperBounds.stream());
  }

  @Override
  public Nary<TypeInstance> lower() {
    return Nary.empty();
  }

  public static UpperOnlyTypeBounds create(List<TypeInstance> upper) {
    UpperOnlyTypeBounds bounds = new UpperOnlyTypeBounds();
    bounds.upperBounds = upper;
    return bounds;
  }

}
