package ar.com.kfgodel.diamond.impl.types.description.descriptors;

import ar.com.kfgodel.diamond.impl.natives.RawClassExtractor;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the helper object that can be used to describe part of an unannotated type
 * Date: 24/11/19 - 01:36
 */
public class UnannotatedTypeDescriptor {

  private Type nativeType;

  public static UnannotatedTypeDescriptor create(Type nativeType) {
    UnannotatedTypeDescriptor descriptor = new UnannotatedTypeDescriptor();
    descriptor.nativeType = nativeType;
    return descriptor;
  }

  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return NaryFromCollectionSupplier.from(RawClassExtractor.fromUnspecific(nativeType));
  }

  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return CachedValue.lazilyBy(() -> {
      final Set<Class<?>> behavioralClasses = getRawClassesSupplier().get().collect(Collectors.toSet());
      final Class<?> firstRawClass = RawClassExtractor.coalesce(behavioralClasses);
      return Nary.of(firstRawClass);
    });
  }

}
