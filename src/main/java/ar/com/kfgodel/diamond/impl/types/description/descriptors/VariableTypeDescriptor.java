package ar.com.kfgodel.diamond.impl.types.description.descriptors;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.VariableTypeInheritanceDescription;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;
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


  public InheritanceDescription getInheritanceDescription() {
    return VariableTypeInheritanceDescription.create(behavioralClasses, typeArguments);
  }

}
