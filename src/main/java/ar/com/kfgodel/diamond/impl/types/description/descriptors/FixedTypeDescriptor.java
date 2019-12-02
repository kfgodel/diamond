package ar.com.kfgodel.diamond.impl.types.description.descriptors;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ArraysConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.ClassConstructorsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type describes unannotated types that are fixed to a single type
 * Date: 24/11/19 - 01:55
 */
public class FixedTypeDescriptor {

  private Type nativeType;
  private Class<?> rawClass;
  private Supplier<Nary<TypeInstance>> typeArguments;

  public static FixedTypeDescriptor create(Type nativeType, Class<?> rawClass, Supplier<Nary<TypeInstance>> typeArguments) {
    FixedTypeDescriptor descriptor = new FixedTypeDescriptor();
    descriptor.nativeType = nativeType;
    descriptor.rawClass = rawClass;
    descriptor.typeArguments = typeArguments;
    return descriptor;
  }

  public InheritanceDescription getInheritanceDescription() {
    return FixedTypeInheritanceDescription.create(rawClass, typeArguments);
  }

  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return GenericTypeParametersSupplier.create(rawClass);
  }

  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    if (rawClass.isArray()) {
      // Artificial constructor for arrays: https://github.com/kfgodel/diamond/issues/88
      return ArraysConstructorSupplier.create(rawClass);
    }
    return ClassConstructorsSupplier.create(rawClass);
  }


}
