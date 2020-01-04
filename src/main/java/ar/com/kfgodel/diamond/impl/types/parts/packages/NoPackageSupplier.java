package ar.com.kfgodel.diamond.impl.types.parts.packages;

import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type representes the package supplier for a type that has no package
 * Date: 24/11/19 - 03:05
 */
public class NoPackageSupplier implements Supplier<Unary<TypePackage>>{
  public static final Supplier<Unary<TypePackage>> INSTANCE = create();

  @Override
  public Unary<TypePackage> get() {
    return Nary.empty();
  }

  public static NoPackageSupplier create() {
    NoPackageSupplier supplier = new NoPackageSupplier();
    return supplier;
  }

}
