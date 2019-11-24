package ar.com.kfgodel.diamond.impl.types.description.descriptors;

import ar.com.kfgodel.diamond.impl.natives.RawClassExtractor;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.Supplier;

/**
 * This type represents the helper object that can be used to describe part of an unannotated type
 * Date: 24/11/19 - 01:36
 */
public class UnannotatedTypeDescriptor {

  private Type nativeType;
  private Class<?> rawClass;
  private Set<Class<?>> rawClasses;

  public static UnannotatedTypeDescriptor create(Type nativeType) {
    UnannotatedTypeDescriptor descriptor = new UnannotatedTypeDescriptor();
    descriptor.nativeType = nativeType;
    return descriptor;
  }

  /**
   * The set of classes that define the behavior of this type.<br>
   * It can be more than one if this is a multiple upper bounded type description.<br>
   * The behavior of this type is then defined as the join of the upper bounds (it's a type that subclasses
   * all this behavioral classes).<br>
   * It can be just one class if this description represents a fixed type
   *
   * @return The list of raw classes that define this type behavior description
   */
  public Set<Class<?>> getBehavioralClasses() {
    if (rawClasses == null) {
      rawClasses = RawClassExtractor.fromUnspecific(nativeType);
    }
    return rawClasses;
  }

  /**
   * @return The class that represents this type without any annotations or generics
   */
  public Class<?> getRawClass() {
    if (rawClass == null) {
      rawClass = RawClassExtractor.coalesce(getBehavioralClasses());
    }
    return rawClass;
  }

  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return NaryFromCollectionSupplier.from(getBehavioralClasses());
  }

  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return () -> Nary.of(getRawClass());
  }

}
