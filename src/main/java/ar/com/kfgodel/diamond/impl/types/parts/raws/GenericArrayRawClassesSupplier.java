package ar.com.kfgodel.diamond.impl.types.parts.raws;

import ar.com.kfgodel.diamond.impl.natives.raws.GenericTypeRawClassExtractor;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.GenericArrayType;
import java.util.Set;
import java.util.function.Supplier;

/**
 * This type represents the supplier for a generic array type
 * Date: 27/11/19 - 21:30
 */
public class GenericArrayRawClassesSupplier implements Supplier<Nary<Class<?>>> {

  private GenericArrayType type;

  @Override
  public Nary<Class<?>> get() {
    final Set<Class<?>> rawClasses = GenericTypeRawClassExtractor.create().apply(type);
    return Nary.from(rawClasses);
  }

  public static GenericArrayRawClassesSupplier create(GenericArrayType type) {
    GenericArrayRawClassesSupplier supplier = new GenericArrayRawClassesSupplier();
    supplier.type = type;
    return supplier;
  }

}
