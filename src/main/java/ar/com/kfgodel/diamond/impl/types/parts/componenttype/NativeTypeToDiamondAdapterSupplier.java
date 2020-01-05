package ar.com.kfgodel.diamond.impl.types.parts.componenttype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;

/**
 * This type represents the supplier of diamond types that uses the native type as basis.<br>
 *   When invoked this returns null if the native type is null
 *
 * Created by kfgodel on 29/09/14.
 */
public class NativeTypeToDiamondAdapterSupplier implements Supplier<TypeInstance> {

  private Supplier<Object> nativeTypeSupplier;

  public static Supplier<TypeInstance> create(Supplier<Object> typeSupplier) {
    NativeTypeToDiamondAdapterSupplier supplier = new NativeTypeToDiamondAdapterSupplier();
    supplier.nativeTypeSupplier = typeSupplier;
    return supplier;
  }

  @Override
  public TypeInstance get() {
    final Object nativeType = nativeTypeSupplier.get();
    if(nativeType == null){
      // Some of the native reflection methods may return null. In that case we don't have a type instance
      return null;
    }
    return Diamond.types().from(nativeType);
  }

}
