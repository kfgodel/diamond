package ar.com.kfgodel.diamond.impl.types.parts.interfaces;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.streams.TypeNarySupplierFromNativeTypeArray;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the supplier for interfaces of a fixed type
 * Created by kfgodel on 04/11/14.
 */
public class ImmutableInterfacesSupplier {

  public static Supplier<Nary<TypeInstance>> create(Class<?> rawClass) {
    return TypeNarySupplierFromNativeTypeArray.apply(rawClass::getInterfaces);
  }
}
