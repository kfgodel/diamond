package ar.com.kfgodel.diamond.impl.types.parts.behavior;

import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a supplier of the raw class for a type that is not based on raw classes
 * Date: 24/11/19 - 03:11
 */
public class NoRawClassSupplier implements Supplier<Nary<Class<?>>>{
  public static final Supplier<Nary<Class<?>>> INSTANCE = create();

  @Override
  public Nary<Class<?>> get() {
    return Nary.empty();
  }

  public static NoRawClassSupplier create() {
    NoRawClassSupplier supplier = new NoRawClassSupplier();
    return supplier;
  }

}
