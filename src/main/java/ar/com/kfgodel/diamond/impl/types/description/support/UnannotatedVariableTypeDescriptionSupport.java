package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.VariableTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.TypeVariableNamesDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.WildCardNamesDescription;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.function.Supplier;

/**
 * This type serves as a base class for descriptions of types that represent variable types
 * (type variables and wildcards)
 * Created by kfgodel on 28/09/14.
 */
public abstract class UnannotatedVariableTypeDescriptionSupport extends UnannotatedTypeDescriptionSupport {

  @Override
  public TypeNamesDescription getNames() {
    Type unannotatedType = getUnannotatedNativeType();
    if (unannotatedType instanceof TypeVariable) {
      TypeVariable typeVariable = (TypeVariable) unannotatedType;
      return TypeVariableNamesDescription.create(typeVariable);
    } else if (unannotatedType instanceof WildcardType) {
      return WildCardNamesDescription.create((WildcardType) unannotatedType);
    }
    throw new DiamondException("Variable type is unknown:" + unannotatedType);
  }

  private Type getUnannotatedNativeType() {
    final Type nativeType = getNativeType();
    if (nativeType instanceof AnnotatedType) {
      // We need to un-annotate it
      return ((AnnotatedType) nativeType).getType();
    }
    return nativeType;
  }

  @Override
  public boolean isForVariableType() {
    return true;
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return Nary::empty;
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return VariableTypeInheritanceDescription.create(getBehavioralClasses(), getTypeArguments());
  }
}
