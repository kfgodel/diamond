package ar.com.kfgodel.diamond.impl.streams;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the supplier of type streams taken from a native type array.<br>
 * As native type arrays don't change over time it's safe to cache the type instance for each native type
 * in a collection and get streams from that collection
 * Created by kfgodel on 01/11/14.
 */
public class TypeNarySupplierFromNativeTypeArray {

  public static Supplier<Nary<TypeInstance>> apply(Supplier<Object[]> nativeTypesSupplier) {
    return NarySupplierFromCollection.lazilyBy(() ->
      Diamond.types().from(nativeTypesSupplier.get())
        .collect(Collectors.toList())
    );
  }
}
