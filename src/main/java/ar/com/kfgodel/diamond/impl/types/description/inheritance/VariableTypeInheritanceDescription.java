package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the inheritance description of a variable type
 * Created by kfgodel on 04/11/14.
 */
public class VariableTypeInheritanceDescription implements InheritanceDescription {

  private Supplier<TypeBounds> bounds;

  @Override
  public Supplier<Nary<TypeInstance>> getExtendedTypeSupplier() {
    return getSuperclassSupplier();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getSuperclassSupplier() {
    return CachedValue.lazilyBy(() -> {
      return getUpperBoundThatAre(Categories.CLASS)
        .orElseUse(()-> Diamond.of(Object.class)); //Object is the implicit upper bound of everything
    });
  }

  private Nary<TypeInstance> getUpperBoundThatAre(TypeCategory expectedCategory) {
    return bounds.get().upper()
      .filter(upperBound -> upperBound.is().partOf(expectedCategory));
  }


  @Override
  public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
    return NarySupplierFromCollection.lazilyBy(() -> {
        return getUpperBoundThatAre(Categories.INTERFACE)
          .collect(Collectors.toList());
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
