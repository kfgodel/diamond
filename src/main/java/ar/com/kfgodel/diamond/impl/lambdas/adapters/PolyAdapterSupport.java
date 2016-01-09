package ar.com.kfgodel.diamond.impl.lambdas.adapters;

/**
 * This type serves as base class for polymorphic adapters
 * Created by kfgodel on 09/01/16.
 */
public abstract class PolyAdapterSupport implements PolyAdapter {

  @Override
  public boolean equals(Object obj) {
    return PolyAdapterEquality.INSTANCE.areEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return PolyAdapterEquality.INSTANCE.hashcodeFor(this);
  }

}
