package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.UnannotatedFixedTypeDescriptor;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a base class for unannotated fixed types
 * Created by kfgodel on 29/09/14.
 */
public abstract class UnannotatedFixedTypeDescriptionSupport extends UnannotatedTypeDescriptionSupport {

  private UnannotatedFixedTypeDescriptor unannotatedFixedTypeDescriptor(){
    return UnannotatedFixedTypeDescriptor.create(getNativeType(), getRawClass(), getTypeArguments());
  }

  @Override
  public Supplier<TypeNames> getNamesSupplier(TypeInstance type) {
    return unannotatedFixedTypeDescriptor().getNamesSupplier(type);
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return unannotatedFixedTypeDescriptor().getInheritanceDescription();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return unannotatedFixedTypeDescriptor().getTypeParametersSupplier();
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return unannotatedFixedTypeDescriptor().getTypeConstructors();
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return unannotatedFixedTypeDescriptor().getDeclaredPackage();
  }

  @Override
  public boolean isForVariableType() {
    return unannotatedFixedTypeDescriptor().isForVariableType();
  }


}
