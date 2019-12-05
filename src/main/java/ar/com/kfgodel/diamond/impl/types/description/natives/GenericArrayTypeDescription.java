package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.ClassTypeNameDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.ArrayComponentTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.packages.TypePackageSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.GenericArrayType;
import java.util.function.Supplier;

/**
 * This type represents an unannotated native generic array type description
 * Created by kfgodel on 29/09/14.
 */
public class GenericArrayTypeDescription extends TypeDescriptionSupport {

  private GenericArrayType nativeType;

  @Override
  public Supplier<Nary<TypeInstance>> getComponentType() {
    return ArrayComponentTypeSupplier.create(nativeType);
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return CachedValue.lazilyBy(()-> TypePackageSupplier.from(getRawClass()));
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return FixedTypeInheritanceDescription.create(getRawClass(), getTypeArguments());
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return ClassTypeNameDescription.create(getRawClass(), nativeType.getTypeName());
  }

  /**
   * @return The class that represents this type without any annotations or generics
   */
  protected Class<?> getRawClass() {
    return getRawClassSupplier().get().get();
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return CachedValue.lazilyBy(()-> RawClassesCalculator.create().from(nativeType));
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    // Only 1 is available
    return getRawClassSupplier();
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return ClassConstructorsSupplier.selectFor(getRawClass());
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return GenericTypeParametersSupplier.create(getRawClass());
  }

  @Override
  public boolean isForVariableType() {
    return false;
  }

  public static GenericArrayTypeDescription create(GenericArrayType nativeType) {
    GenericArrayTypeDescription description = new GenericArrayTypeDescription();
    description.nativeType = nativeType;
    return description;
  }

}
