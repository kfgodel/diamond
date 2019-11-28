package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.FixedTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.UnannotatedTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.ArrayComponentTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.raws.GenericArrayRawClassesSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents an unannotated native generic array type description
 * Created by kfgodel on 29/09/14.
 */
public class GenericArrayTypeDescription extends TypeDescriptionSupport {

  private GenericArrayType nativeType;

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return unannotatedFixedTypeDescriptor().getDeclaredPackage();
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return unannotatedFixedTypeDescriptor().getInheritanceDescription();
  }

  @Override
  public Supplier<TypeNames> getNamesSupplier(TypeInstance type) {
    return unannotatedFixedTypeDescriptor().getNamesSupplier(type);
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
    return getRawClassSupplier().get().get();
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return unnanotatedTypeDescriptor().getRawClassSupplier();
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return GenericArrayRawClassesSupplier.create(nativeType);
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

  protected UnannotatedTypeDescriptor unnanotatedTypeDescriptor(){
    return UnannotatedTypeDescriptor.create(getNativeType());
  }

  public static GenericArrayTypeDescription create(GenericArrayType nativeType) {
    GenericArrayTypeDescription description = new GenericArrayTypeDescription();
    description.nativeType = nativeType;
    return description;
  }

}
