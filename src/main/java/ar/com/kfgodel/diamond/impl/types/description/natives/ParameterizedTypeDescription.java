package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.FixedTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.ClassTypeNameDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.packages.TypePackageSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.ParameterizedTypeArgumentsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents the description of an unannotated parameterized native type
 * Created by kfgodel on 29/09/14.
 */
public class ParameterizedTypeDescription extends TypeDescriptionSupport {

  private ParameterizedType nativeType;

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return TypePackageSupplier.create(getRawClass());
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return FixedTypeInheritanceDescription.create(getRawClass(), getTypeArguments());
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return ClassTypeNameDescription.create(getRawClass(), nativeType.getTypeName());
  }

  protected Type getNativeType() {
    return nativeType;
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
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return ParameterizedTypeArgumentsSupplier.create(nativeType);
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return unannotatedFixedTypeDescriptor().getTypeConstructors();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return GenericTypeParametersSupplier.create(getRawClass());
  }

  @Override
  public boolean isForVariableType() {
    return false;
  }

  protected FixedTypeDescriptor unannotatedFixedTypeDescriptor(){
    return FixedTypeDescriptor.create(getNativeType(), getRawClassSupplier().get().get(), getTypeArguments());
  }

  public static ParameterizedTypeDescription create(ParameterizedType nativeType) {
    ParameterizedTypeDescription description = new ParameterizedTypeDescription();
    description.nativeType = nativeType;
    return description;
  }

}
