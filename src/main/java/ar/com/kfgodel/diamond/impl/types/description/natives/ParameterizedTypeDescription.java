package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.ClassTypeNameDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorExtractor;
import ar.com.kfgodel.diamond.impl.types.parts.packages.TypePackageSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.lang.reflect.ParameterizedType;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the description of an unannotated parameterized native type
 * Created by kfgodel on 29/09/14.
 */
public class ParameterizedTypeDescription extends TypeDescriptionSupport {

  private ParameterizedType nativeType;

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return CachedValue.from(() -> TypePackageSupplier.from(getRawClass()));
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
    return RawClassesCalculator.create().from(nativeType)
      .orElseThrow(() -> new DiamondException("Parameterized type[" + nativeType +
        "] doesn't have a raw class in runtime?"));
  }

  @Override
  public Supplier<Nary<Object>> getReflectionTypeSupplier() {
    return CachedValue.from(() -> Nary.of(this.nativeType));
  }

  @Override
  public Supplier<Nary<Class<?>>> getRuntimeClasses() {
    return CachedValue.from(() -> Nary.of(getRawClass()));
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return NarySupplierFromCollection.lazilyBy(() -> {
      return Diamond.types().from(nativeType.getActualTypeArguments())
        .collect(Collectors.toList());
    });
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return NarySupplierFromCollection.lazilyBy(()-> {
      return ClassConstructorExtractor.create(getRawClass())
        .get()
        .collectToList();
    });
  }

  @Override
  public BiPredicate<TypeInstance, Object> getInstancePredicate() {
    // We ignore the type parameter as we already have the runtime class
    return (type, instance) -> getRawClass().isInstance(instance);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return GenericTypeParametersSupplier.create(getRawClass());
  }

  @Override
  public boolean isForVariableType() {
    return false;
  }

  public static ParameterizedTypeDescription create(ParameterizedType nativeType) {
    ParameterizedTypeDescription description = new ParameterizedTypeDescription();
    description.nativeType = nativeType;
    return description;
  }
}
