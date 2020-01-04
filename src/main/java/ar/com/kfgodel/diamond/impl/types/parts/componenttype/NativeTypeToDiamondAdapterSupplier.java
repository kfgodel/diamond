package ar.com.kfgodel.diamond.impl.types.parts.componenttype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents a supplier that gets a native reflection instance and converts it to
 * diamond when asked
 *
 * Created by kfgodel on 29/09/14.
 */
public class NativeTypeToDiamondAdapterSupplier implements Supplier<Unary<TypeInstance>> {

  private Supplier<Object> nativeTypeSupplier;

  public static Supplier<Unary<TypeInstance>> create(Supplier<Object> typeSupplier) {
    NativeTypeToDiamondAdapterSupplier supplier = new NativeTypeToDiamondAdapterSupplier();
    supplier.nativeTypeSupplier = typeSupplier;
    return supplier;
  }

  @Override
  public Unary<TypeInstance> get() {
    return Nary.of(nativeTypeSupplier.get())
      .map(componentType -> Diamond.types().from(componentType))
      .unique();
  }

}
