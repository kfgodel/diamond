package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ExtendedTypeCalculator;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ImplementedTypesCalculator;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.SuperClassSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents the inheritance description of a fixed type
 * Created by kfgodel on 04/11/14.
 */
public class FixedTypeInheritanceDescription implements InheritanceDescription {

  private Class<?> rawClass;
  private Supplier<Nary<TypeInstance>> typeArguments;

  @Override
  public Supplier<Unary<TypeInstance>> getSuperclassSupplier() {
    return SuperClassSupplier.create(rawClass);
  }

  @Override
  public Supplier<Unary<TypeInstance>> getExtendedTypeSupplier() {
    return CachedValue.from(ExtendedTypeCalculator.create(rawClass, typeArguments));
  }

  @Override
  public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
    return CachedValues.adapting(() -> {
      return Diamond.types().from(rawClass.getInterfaces());
    });
  }

  @Override
  public Supplier<Nary<TypeInstance>> getImplementedTypesSupplier() {
    return CachedValues.adapting(
      ImplementedTypesCalculator.create(rawClass, typeArguments)
    );
  }

  public static FixedTypeInheritanceDescription create(Class<?> rawClass, Supplier<Nary<TypeInstance>> typeArguments) {
    FixedTypeInheritanceDescription description = new FixedTypeInheritanceDescription();
    description.rawClass = rawClass;
    description.typeArguments = typeArguments;
    return description;
  }

}
