package ar.com.kfgodel.diamond.impl.types.description.descriptors;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.VariableTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.TypeVariableNamesDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.WildCardNamesDescription;
import ar.com.kfgodel.diamond.impl.types.names.TypeInstanceNames;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;
import java.util.function.Supplier;

/**
 * This type describes unannotated type which can vary
 * Date: 24/11/19 - 02:11
 */
public class VariableTypeDescriptor {

  private Type nativeType;
  private Set<Class<?>> behavioralClasses;
  private Supplier<Nary<TypeInstance>> typeArguments;

  public static VariableTypeDescriptor create(Type nativeType, Set<Class<?>> behavioralClasses, Supplier<Nary<TypeInstance>> typeArguments) {
    VariableTypeDescriptor descriptor = new VariableTypeDescriptor();
    descriptor.nativeType = nativeType;
    descriptor.behavioralClasses = behavioralClasses;
    descriptor.typeArguments = typeArguments;
    return descriptor;
  }


  public Supplier<TypeNames> getNamesSupplier(TypeInstance type) {
    return ()-> TypeInstanceNames.create(type, describeNames());
  }

  private TypeNamesDescription describeNames() {
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
    if (nativeType instanceof AnnotatedType) {
      // We need to un-annotate it
      return ((AnnotatedType) nativeType).getType();
    }
    return nativeType;
  }

  public boolean isForVariableType() {
    return true;
  }

  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return Nary::empty;
  }

  public InheritanceDescription getInheritanceDescription() {
    return VariableTypeInheritanceDescription.create(behavioralClasses, typeArguments);
  }

}
