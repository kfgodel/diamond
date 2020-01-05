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
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.NoInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.UnnamedTypeDescription;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.types.names.TypeInstanceNames;
import ar.com.kfgodel.diamond.impl.types.parts.fields.ClassFieldCalculator;
import ar.com.kfgodel.diamond.impl.types.parts.methods.ClassMethodCalculator;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.annotation.Annotation;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This class serves as a base template for a type description with sensible defaults for most methods
 * Date: 24/11/19 - 02:47
 */
public abstract class TypeDescriptionSupport implements TypeDescription {

  public Supplier<Nary<Annotation>> getAnnotations() {
    return Nary::empty;
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
    return ()-> NoBounds.INSTANCE;
  }

  @Override
  public Function<TypeInstance, Supplier<? extends Nary<TypeCategory>>> getCategoriesCalculator() {
    return (givenType) -> {
      return CachedValues.adapting(() ->
        Categories.values()
          .filter((category) -> category.contains(givenType))
      );
    };
  }

  public Supplier<Unary<TypeInstance>> getComponentType() {
    return Nary::empty;
  }

  @Override
  public Supplier<Unary<TypePackage>> getDeclaredPackage() {
    return Nary::empty;
  }

  @Override
  public Function<TypeInstance, Object> getIdentityToken() {
    return CachedTokenCalculator.create(TypeEquality.INSTANCE::calculateTokenFor);
  }

  public InheritanceDescription getInheritanceDescription() {
    return NoInheritanceDescription.INSTANCE;
  }

  @Override
  public BiPredicate<TypeInstance, Object> getInstancePredicate() {
    return  (testedType, testedInstance) -> {
      return testedType.runtime().classes()
        .anyMatch((rawType) -> rawType.isInstance(testedInstance));
    };
  }


  @Override
  public Function<TypeInstance, Supplier<TypeNames>> getNamesCalculator() {
    return (type) -> {
      return CachedValue.from(() -> TypeInstanceNames.create(type, this.getNamesDescription()));
    };
  }

  protected TypeNamesDescription getNamesDescription() {
    return UnnamedTypeDescription.create("<unknown>");
  }

  @Override
  public Supplier<Unary<Object>> getReflectionTypeSupplier() {
    return Nary::empty; //By default there's no reflected object for this type
  }

  @Override
  public Supplier<? extends Nary<Class<?>>> getRuntimeClasses() {
    return Nary::empty;
  }

  @Override
  public Supplier<Unary<TypeInstance>> getRuntimeType() {
    return () -> {
      final Nary<Class<?>> runtimeClasses = getRuntimeClasses().get();
      final Class<?> runtimeClass = RawClassesCalculator.create().coalesce(runtimeClasses);
      return Nary.of(Diamond.of(runtimeClass));
    };
  }

  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return Nary::empty;
  }

  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return Nary::empty;
  }

  public Supplier<Nary<TypeField>> getTypeFields() {
    return CachedValues.adapting(ClassFieldCalculator.create(getRuntimeClasses()));
  }

  public Supplier<Nary<TypeMethod>> getTypeMethods() {
    return CachedValues.adapting(ClassMethodCalculator.create(getRuntimeClasses()));
  }

  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return Nary::empty;
  }

  @Override
  public boolean isForVariableType() {
    return true;
  }

}
