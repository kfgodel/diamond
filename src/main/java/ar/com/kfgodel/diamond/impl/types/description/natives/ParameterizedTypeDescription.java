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
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;
import ar.com.kfgodel.nary.impl.UnaryWrapper;

import java.lang.reflect.ParameterizedType;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * This type represents the description of an unannotated parameterized native type
 * Created by kfgodel on 29/09/14.
 */
public class ParameterizedTypeDescription extends TypeDescriptionSupport {

  private ParameterizedType nativeType;
  private Supplier<Class<?>> rawClassSupplier;

  @Override
  public Supplier<Unary<TypePackage>> getDeclaredPackage() {
    return UnaryWrapper.supply(CachedValue.from(() ->
      TypePackageSupplier.create(rawClassSupplier.get()).get()
    ));
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return FixedTypeInheritanceDescription.create(rawClassSupplier.get(), getTypeArguments());
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return ClassTypeNameDescription.create(rawClassSupplier.get(), nativeType.getTypeName());
  }

  @Override
  public Supplier<Unary<Object>> getReflectionTypeSupplier() {
    return ()-> Nary.of(this.nativeType);
  }

  @Override
  public Supplier<Unary<Class<?>>> getRuntimeClasses() {
    return UnaryWrapper.supply(rawClassSupplier);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return CachedValues.adapting(() ->
      Diamond.types().from(nativeType.getActualTypeArguments())
    );
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return CachedValues.adapting(ClassConstructorExtractor.create(rawClassSupplier.get()));
  }

  @Override
  public BiPredicate<TypeInstance, Object> getInstancePredicate() {
    // We ignore the type parameter as we already have the runtime class
    return (type, instance) -> rawClassSupplier.get().isInstance(instance);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return CachedValues.adapting(() ->
      Diamond.types().from(rawClassSupplier.get().getTypeParameters())
    );
  }

  @Override
  public boolean isForVariableType() {
    return false;
  }

  public static ParameterizedTypeDescription create(ParameterizedType nativeType) {
    ParameterizedTypeDescription description = new ParameterizedTypeDescription();
    description.nativeType = nativeType;
    description.rawClassSupplier = CachedValue.from(()-> calculateRawClass(nativeType));
    return description;
  }

  /**
   * @return The class that represents this type without any annotations or generics
   */
  private static Class<?> calculateRawClass(ParameterizedType nativeType) {
    return RawClassesCalculator.create().from(nativeType)
      .unique()
      .orElseThrow(() ->
        new DiamondException("Parameterized type[" + nativeType + "] doesn't have a raw class at runtime?")
      );
  }

}
