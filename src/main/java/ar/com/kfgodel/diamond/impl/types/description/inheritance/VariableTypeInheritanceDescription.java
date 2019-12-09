package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the inheritance description of a variable type
 * Created by kfgodel on 04/11/14.
 */
public class VariableTypeInheritanceDescription implements InheritanceDescription {


  private Supplier<Nary<Class<?>>> runtimeClasses;
  private Supplier<Nary<TypeInstance>> typeArguments;

  @Override
  public Supplier<Nary<TypeInstance>> getSuperclassSupplier() {
    return CachedValue.lazilyBy(() -> Nary.of(Diamond.of(getParentClassFromUpperBounds())));
  }

  /**
   * Tries to get the only class used as upper bound (if any), if none is found, then
   * object is used as parent type
   *
   * @return The type to use as parent of this type variable
   */
  private Class<?> getParentClassFromUpperBounds() {
    // We look for the only allowed class as upper bound
    final Nary<Class<?>> upperBoundClasses = this.runtimeClasses.get()
      .filter((upper) -> !upper.isInterface());
    return RawClassesCalculator.create().coalesce(upperBoundClasses);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getExtendedTypeSupplier() {
    return getSuperclassSupplier();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
    return NaryFromCollectionSupplier.lazilyBy(() -> runtimeClasses.get()
      .filter(Class::isInterface)
      .map(Diamond::of)
      .collect(Collectors.toList())
    );
  }

  @Override
  public Supplier<Nary<TypeInstance>> getImplementedTypesSupplier() {
    return getInterfacesSupplier();
  }

  public static VariableTypeInheritanceDescription create(Supplier<Nary<Class<?>>> runtimeClasses,
                                                          Supplier<Nary<TypeInstance>> typeArguments) {
    VariableTypeInheritanceDescription description = new VariableTypeInheritanceDescription();
    description.runtimeClasses = runtimeClasses;
    description.typeArguments = typeArguments;
    return description;
  }

}
