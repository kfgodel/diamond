package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.NoInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.UnnamedTypeDescription;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.behavior.NoRawClassesSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.NoBoundsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.NoComponentTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.NonInstantiableConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.fields.ClassFieldSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.methods.ClassMethodSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.packages.NoPackageSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.NoTypeArgumentsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.NoTypeParametersSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.annotation.Annotation;
import java.util.function.BiPredicate;
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

  @Override
  public BiPredicate<TypeInstance, TypeInstance> getAssignabilityPredicate() {
    // We check if any of our runtime classes is assignable from any of the other runtime classes
    // Note: this seems flawed as there could be a type in the other that is not assignable to to this
    return (thisInstance, otherInstance) ->{
      return thisInstance.runtime().classes().anyMatch(thisRuntimeClass->{
        return otherInstance.runtime().classes().anyMatch(thisRuntimeClass::isAssignableFrom);
      });
    };
  }

  public Supplier<TypeBounds> getBounds() {
    return NoBoundsSupplier.INSTANCE;
  }

  public Supplier<Nary<TypeInstance>> getComponentType() {
    return NoComponentTypeSupplier.INSTANCE;
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return NoPackageSupplier.INSTANCE;
  }

  @Override
  public Function<TypeInstance, Object> getIdentityToken() {
    return CachedTokenCalculator.create(TypeEquality.INSTANCE::calculateTokenFor);
  }

  public InheritanceDescription getInheritanceDescription() {
    return NoInheritanceDescription.INSTANCE;
  }

  @Override
  public Function<TypeInstance, Supplier<Nary<TypeCategory>>> getCategoriesCalculator() {
    return (givenType) -> {
      return NaryFromCollectionSupplier.lazilyBy(() ->
        Categories.values()
          .filter((category) -> category.contains(givenType))
          .collect(Collectors.toList())
      );
    };
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return UnnamedTypeDescription.create("<unknown>");
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return NoRawClassesSupplier.INSTANCE;
  }

  @Override
  public Supplier<Nary<Object>> getReflectionTypeSupplier() {
    return Nary::empty; //By default there's no reflected object for this type
  }

  @Override
  public Supplier<Nary<Class<?>>> getRuntimeClasses() {
    return Nary::empty;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getRuntimeType() {
    return () -> {
      final Nary<Class<?>> runtimeClasses = getRuntimeClasses().get();
      final Class<?> runtimeClass = RawClassesCalculator.create().coalesce(runtimeClasses);
      return Nary.of(Diamond.of(runtimeClass));
    };
  }

  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return NoTypeArgumentsSupplier.INSTANCE;
  }

  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return NonInstantiableConstructorSupplier.INSTANCE;
  }

  public Supplier<Nary<TypeField>> getTypeFields() {
    return ClassFieldSupplier.create(getRawClassesSupplier());
  }

  public Supplier<Nary<TypeMethod>> getTypeMethods() {
    return ClassMethodSupplier.create(getRawClassesSupplier());
  }

  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return NoTypeParametersSupplier.INSTANCE;
  }

  @Override
  public Predicate<Object> getInstancePredicate() {
    return  (testedInstance) -> {
      return getRuntimeClasses().get()
        .anyMatch((rawType) -> rawType.isInstance(testedInstance));
    };
  }

  @Override
  public boolean isForVariableType() {
    return true;
  }

}
