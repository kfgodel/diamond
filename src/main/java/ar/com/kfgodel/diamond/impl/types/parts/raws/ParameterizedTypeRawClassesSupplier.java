package ar.com.kfgodel.diamond.impl.types.parts.raws;

import ar.com.kfgodel.diamond.impl.natives.raws.ParameterizedRawClassExtractor;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.ParameterizedType;
import java.util.Set;
import java.util.function.Supplier;

/**
 * This type represents the supplier for a parameterized type
 * Date: 27/11/19 - 21:25
 */
public class ParameterizedTypeRawClassesSupplier implements Supplier<Nary<Class<?>>> {

  private ParameterizedType type;

  @Override
  public Nary<Class<?>> get() {
    final Set<Class<?>> rawClasses = ParameterizedRawClassExtractor.create().apply(type);
    return Nary.from(rawClasses);
  }

  public static ParameterizedTypeRawClassesSupplier create(ParameterizedType type) {
    ParameterizedTypeRawClassesSupplier supplier = new ParameterizedTypeRawClassesSupplier();
    supplier.type = type;
    return supplier;
  }

}
