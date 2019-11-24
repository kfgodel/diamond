package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.UnannotatedVariableTypeDescriptor;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type serves as a base class for descriptions of types that represent variable types
 * (type variables and wildcards)
 * Created by kfgodel on 28/09/14.
 */
public abstract class UnannotatedVariableTypeDescriptionSupport extends UnannotatedTypeDescriptionSupport {

  private UnannotatedVariableTypeDescriptor unannotatedVariableTypeDescriptor(){
    return UnannotatedVariableTypeDescriptor.create(getNativeType(), getBehavioralClasses(), getTypeArguments());
  }

  @Override
  public Supplier<TypeNames> getNamesSupplier(TypeInstance type) {
    return unannotatedVariableTypeDescriptor().getNamesSupplier(type);
  }

  @Override
  public boolean isForVariableType() {
    return unannotatedVariableTypeDescriptor().isForVariableType();
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return unannotatedVariableTypeDescriptor().getDeclaredPackage();
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return unannotatedVariableTypeDescriptor().getInheritanceDescription();
  }
}
