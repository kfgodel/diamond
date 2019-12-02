package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.FixedTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.names.ClassTypeNameDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.ArrayComponentTypeSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents the description of an unannotated native class
 * Created by kfgodel on 29/09/14.
 */
public class ClassDescription extends TypeDescriptionSupport {

  private Class<?> nativeType;

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return unannotatedFixedTypeDescriptor().getDeclaredPackage();
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return unannotatedFixedTypeDescriptor().getInheritanceDescription();
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return ClassTypeNameDescription.create(getRawClass(), nativeType.getTypeName());
  }

  protected Type getNativeType() {
    return nativeType;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getComponentType() {
    return ArrayComponentTypeSupplier.create(nativeType);
  }

  /**
   * @return The class that represents this type without any annotations or generics
   */
  protected Class<?> getRawClass() {
    return nativeType;
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
    return unannotatedFixedTypeDescriptor().getTypeConstructors();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return unannotatedFixedTypeDescriptor().getTypeParametersSupplier();
  }

  @Override
  public boolean isForVariableType() {
    return unannotatedFixedTypeDescriptor().isForVariableType();
  }

  protected FixedTypeDescriptor unannotatedFixedTypeDescriptor(){
    return FixedTypeDescriptor.create(getNativeType(), getRawClass(), getTypeArguments());
  }

  public static ClassDescription create(Class<?> nativeType) {
    ClassDescription description = new ClassDescription();
    description.nativeType = nativeType;
    return description;
  }

}
