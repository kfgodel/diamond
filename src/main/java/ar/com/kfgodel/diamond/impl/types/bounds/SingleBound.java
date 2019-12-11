package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.util.function.Supplier;

/**
 * This type represents a type boundary with only upper bounds (common for type variables)
 * Created by kfgodel on 20/09/14.
 */
public class SingleBound implements TypeBounds {

  private Supplier<Nary<TypeInstance>> upperBounds;

  @Override
  public Nary<TypeInstance> upper() {
    return upperBounds.get();
  }

  @Override
  public Nary<TypeInstance> lower() {
    return NoBounds.INSTANCE.lower();
  }

  public static SingleBound create(Nary<TypeInstance> upper) {
    SingleBound bounds = new SingleBound();
    bounds.upperBounds = NarySupplierFromCollection.lazilyFrom(upper);
    return bounds;
  }

}
