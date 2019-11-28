package ar.com.kfgodel.diamond.impl.types.parts.raws;

import ar.com.kfgodel.diamond.impl.natives.raws.NativeClassRawClassExtractor;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Set;
import java.util.function.Supplier;

/**
 * This type represents the supplier for a simple class
 * Date: 27/11/19 - 21:32
 */
public class ClassRawClassesSupplier implements Supplier<Nary<Class<?>>> {

  private Class<?> type;

  @Override
  public Nary<Class<?>> get() {
    final Set<Class<?>> rawClasses = NativeClassRawClassExtractor.create().apply(type);
    return Nary.from(rawClasses);
  }

  public static ClassRawClassesSupplier create(Class<?> nativeType) {
    ClassRawClassesSupplier supplier = new ClassRawClassesSupplier();
    supplier.type = nativeType;
    return supplier;
  }

}
