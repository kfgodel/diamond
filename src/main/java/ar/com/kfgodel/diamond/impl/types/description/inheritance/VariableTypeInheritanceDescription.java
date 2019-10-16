package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.interfaces.VariableTypeInterfaceSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Set;
import java.util.function.Supplier;

/**
 * This type represents the inheritance description of a variable type
 * Created by kfgodel on 04/11/14.
 */
public class VariableTypeInheritanceDescription implements InheritanceDescription {


  private Set<Class<?>> upperBoundClasses;
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
    Nary<Class<?>> optionalClass = Nary.create(
      upperBoundClasses.stream()
        .filter((upper) -> !upper.isInterface())
    );
    return optionalClass.orElse(Object.class);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getExtendedTypeSupplier() {
    return getSuperclassSupplier();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
    return VariableTypeInterfaceSupplier.create(upperBoundClasses);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getImplementedTypesSupplier() {
    return getInterfacesSupplier();
  }

  public static VariableTypeInheritanceDescription create(Set<Class<?>> rawClasses, Supplier<Nary<TypeInstance>> typeArguments) {
    VariableTypeInheritanceDescription description = new VariableTypeInheritanceDescription();
    description.upperBoundClasses = rawClasses;
    description.typeArguments = typeArguments;
    return description;
  }

}
