package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents the inheritance description of a variable type
 * Created by kfgodel on 04/11/14.
 */
public class VariableTypeInheritanceDescription implements InheritanceDescription {

  private Supplier<TypeBounds> bounds;

  @Override
  public Supplier<Unary<TypeInstance>> getExtendedTypeSupplier() {
    return getSuperclassSupplier();
  }

  @Override
  public Supplier<Unary<TypeInstance>> getSuperclassSupplier() {
    return CachedValue.from(() -> {
      return getUpperBoundsThatAre(Categories.CLASS).asUni()
        .orElseUse(()-> Diamond.of(Object.class)); //Object is the implicit upper bound of everything
    });
  }

  private Nary<TypeInstance> getUpperBoundsThatAre(TypeCategory expectedCategory) {
    return bounds.get().upper()
      .filter(upperBound -> upperBound.is().partOf(expectedCategory));
  }


  @Override
  public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
    return CachedValues.adapting(() -> {
        return getUpperBoundsThatAre(Categories.INTERFACE);
      }
    );
  }

  @Override
  public Supplier<Nary<TypeInstance>> getImplementedTypesSupplier() {
    return getInterfacesSupplier();
  }

  public static VariableTypeInheritanceDescription create(Supplier<TypeBounds> bounds) {
    VariableTypeInheritanceDescription description = new VariableTypeInheritanceDescription();
    description.bounds = bounds;
    return description;
  }

}
