package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.Kinds;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.NoInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.behavior.NoRawClassSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.behavior.NoRawClassesSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.NoBoundsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.NoComponentTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.NonInstantiableConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.fields.ClassFieldSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.methods.ClassMethodSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.NoNamesSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.packages.NoPackageSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.NoTypeArgumentsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.NoTypeParametersSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class serves as a base template for a type description with sensible defaults for most methods
 * Date: 24/11/19 - 02:47
 */
public abstract class TypeDescriptionSupport implements TypeDescription {

  public Supplier<Nary<Annotation>> getAnnotations() {
    return NoAnnotationsSupplier.INSTANCE;
  }

  public InheritanceDescription getInheritanceDescription() {
    return NoInheritanceDescription.INSTANCE;
  }

  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return NoTypeArgumentsSupplier.INSTANCE;
  }

  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return NoTypeParametersSupplier.INSTANCE;
  }

  public Supplier<Nary<TypeInstance>> getComponentType() {
    return NoComponentTypeSupplier.INSTANCE;
  }

  public Supplier<TypeBounds> getBounds() {
    return NoBoundsSupplier.INSTANCE;
  }

  public Supplier<Nary<TypeMethod>> getTypeMethods() {
    return ClassMethodSupplier.create(getRawClassesSupplier());
  }

  public Supplier<Nary<TypeField>> getTypeFields() {
    return ClassFieldSupplier.create(getRawClassesSupplier());
  }

  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return NonInstantiableConstructorSupplier.INSTANCE;
  }

  @Override
  public Supplier<TypeNames> getNamesSupplier(TypeInstance type) {
    return NoNamesSupplier.create(type);
  }

  @Override
  public boolean isForVariableType() {
    return true;
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return NoPackageSupplier.INSTANCE;
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return NoRawClassesSupplier.INSTANCE;
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return NoRawClassSupplier.INSTANCE;
  }

  @Override
  public Function<TypeInstance, Object> getIdentityToken() {
    return CachedTokenCalculator.create(TypeEquality.INSTANCE::calculateTokenFor);
  }

  @Override
  public Supplier<Nary<Kind>> getKindsFor(TypeInstance type) {
    return NaryFromCollectionSupplier.lazilyBy(() -> Arrays.stream(Kinds.values())
      .filter((kind) -> kind.contains(type))
      .collect(Collectors.toList()));
  }

  @Override
  public Predicate<Object> getTypeForPredicate() {
    return  (testedInstance) -> {
      return getRawClassesSupplier().get()
        .anyMatch((rawType) -> rawType.isInstance(testedInstance));
    };
  }

  @Override
  public Predicate<TypeInstance> getAssignabilityPredicate() {
    // We check if any of our native types is assignable from any of the other native types
    // Note: this seems flawed as there could be a type in the other that is not assignable to to this
    return (otherTypeInstance) -> {
      return getRawClassesSupplier().get()
        .anyMatch((thisRawType) -> {
          return otherTypeInstance.nativeTypes()
            .anyMatch(thisRawType::isAssignableFrom);
        });
    };
  }

  @Override
  public Supplier<TypeInstance> getRuntimeType() {
    // We assume that, at least, Object is the minimum type used on runtime to represent
    // instances of this type (true for type variables or wildcards)
    return CachedValue.lazilyBy(() -> {
      final Class<?> runtimeClass = getRawClassSupplier()
        .get()
        .orElse(Object.class);
      return Diamond.of(runtimeClass);
    });
  }

}
