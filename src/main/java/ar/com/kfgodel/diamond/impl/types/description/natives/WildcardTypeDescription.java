package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleBounds;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.VariableTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.WildCardNamesDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.lang.reflect.WildcardType;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the description of an unannotated native wildcard type
 * Created by kfgodel on 28/09/14.
 */
public class WildcardTypeDescription extends TypeDescriptionSupport {

  private WildcardType nativeType;

  @Override
  public Supplier<TypeBounds> getBounds() {
    return CachedValue.from(() -> {
      // Note that Wildcard doesn't have annotated bounds, in contrast to TypeVariable
      final Nary<TypeInstance> upperBounds = Diamond.types().from(nativeType.getUpperBounds());
      final Nary<TypeInstance> lowerBounds = Diamond.types().from(nativeType.getLowerBounds());
      return DoubleBounds.create(upperBounds, lowerBounds);
    });
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return Nary::empty;
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return VariableTypeInheritanceDescription.create(getBounds());
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return WildCardNamesDescription.create(nativeType);
  }

  @Override
  public Supplier<Nary<Object>> getReflectionTypeSupplier() {
    return CachedValue.from(()-> Nary.of(this.nativeType));
  }

  @Override
  public Supplier<Nary<Class<?>>> getRuntimeClasses() {
    return NarySupplierFromCollection.lazilyBy(()-> {
      return RawClassesCalculator.create()
        .from(nativeType)
        .collect(Collectors.toSet());
    });
  }

  @Override
  public boolean isForVariableType() {
    return true;
  }

  public static WildcardTypeDescription create(WildcardType nativeType) {
    WildcardTypeDescription description = new WildcardTypeDescription();
    description.nativeType = nativeType;
    return description;
  }


}
