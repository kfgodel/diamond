package ar.com.kfgodel.diamond.impl.types.parts.raws;

import ar.com.kfgodel.diamond.impl.natives.raws.WildcardTypeRawClassExtractor;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.WildcardType;
import java.util.Set;
import java.util.function.Supplier;

/**
 * This type represents the supplier for a wildcard type
 * Date: 27/11/19 - 21:34
 */
public class WildcardRawClassesSupplier implements Supplier<Nary<Class<?>>> {

  private WildcardType type;

  @Override
  public Nary<Class<?>> get() {
    final Set<Class<?>> rawClasses = WildcardTypeRawClassExtractor.create().apply(type);
    return Nary.from(rawClasses);
  }

  public static WildcardRawClassesSupplier create(WildcardType type) {
    WildcardRawClassesSupplier supplier = new WildcardRawClassesSupplier();
    supplier.type = type;
    return supplier;
  }

}
