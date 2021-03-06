package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.ClassTypeNameDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.NativeTypeToDiamondAdapterSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorExtractor;
import ar.com.kfgodel.diamond.impl.types.parts.packages.TypePackageSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;
import ar.com.kfgodel.nary.impl.UnaryWrapper;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * This type represents the description of an unannotated native class
 * Created by kfgodel on 29/09/14.
 */
public class ClassDescription extends TypeDescriptionSupport {

  private Class<?> nativeType;

  @Override
  public Supplier<Unary<TypeInstance>> getComponentType() {
    return UnaryWrapper.supply(CachedValue.from(
      NativeTypeToDiamondAdapterSupplier.create(nativeType::getComponentType)
    ));
  }

  @Override
  public Supplier<Unary<TypePackage>> getDeclaredPackage() {
    return UnaryWrapper.supply(CachedValue.from(
      TypePackageSupplier.create(getRawClass())
    ));
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
  private Class<?> getRawClass() {
    return nativeType;
  }

  @Override
  public Supplier<Unary<Object>> getReflectionTypeSupplier() {
    return UnaryWrapper.supply(this::getRawClass);
  }

  @Override
  public Supplier<Unary<Class<?>>> getRuntimeClasses() {
    return UnaryWrapper.supply(this::getRawClass);
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return CachedValues.adapting(ClassConstructorExtractor.create(getRawClass()));
  }

  @Override
  public BiPredicate<TypeInstance, Object> getInstancePredicate() {
    // We ignore the type parameter as we already have the runtime class
    return (type, instance) -> getRawClass().isInstance(instance);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return CachedValues.adapting(() -> {
      return Diamond.types().from(getRawClass().getTypeParameters());
    });
  }

  @Override
  public boolean isForVariableType() {
    return false;
  }

  public static ClassDescription create(Class<?> nativeType) {
    ClassDescription description = new ClassDescription();
    description.nativeType = nativeType;
    return description;
  }

}
