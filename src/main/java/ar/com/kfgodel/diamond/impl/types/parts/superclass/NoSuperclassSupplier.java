package ar.com.kfgodel.diamond.impl.types.parts.superclass;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents a supplier for types that have no super class
 * Created by kfgodel on 28/09/14.
 */
public class NoSuperclassSupplier implements Supplier<Unary<TypeInstance>> {

  public static final NoSuperclassSupplier INSTANCE = new NoSuperclassSupplier();

  @Override
  public Unary<TypeInstance> get() {
    return Nary.empty();
  }
}
