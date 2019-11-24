package ar.com.kfgodel.diamond.impl.types.parts.methods;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the method supplier for a type with no methods
 * Date: 24/11/19 - 02:49
 */
public class NoMethodsSupplier implements Supplier<Nary<TypeMethod>> {
  public static final Supplier<Nary<TypeMethod>> INSTANCE = create();

  @Override
  public Nary<TypeMethod> get() {
    return Nary.empty();
  }

  public static NoMethodsSupplier create() {
    NoMethodsSupplier supplier = new NoMethodsSupplier();
    return supplier;
  }

}
