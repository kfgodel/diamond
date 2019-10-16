package ar.com.kfgodel.diamond.impl.types.parts.bounds;

import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;

import java.util.function.Supplier;

/**
 * This type represents the supplier of a type that has no bounds
 * Created by kfgodel on 28/09/14.
 */
public class NoBoundsSupplier implements Supplier<TypeBounds> {

  public static final NoBoundsSupplier INSTANCE = new NoBoundsSupplier();

  @Override
  public TypeBounds get() {
    return NoBounds.INSTANCE;
  }
}
