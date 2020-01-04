package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.annotation.Annotation;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a description that delegates part of it to another description
 * Created by kfgodel on 29/09/14.
 */
public abstract class DelegatedDescriptionSupport implements TypeDescription {

  @Override
  public Supplier<Nary<Annotation>> getAnnotations() {
    return getDelegateDescription().getAnnotations();
  }

  @Override
  public BiPredicate<TypeInstance, TypeInstance> getAssignabilityPredicate() {
    return getDelegateDescription().getAssignabilityPredicate();
  }

  @Override
  public Supplier<TypeBounds> getBounds() {
    return getDelegateDescription().getBounds();
  }

  @Override
  public Supplier<Unary<TypeInstance>> getComponentType() {
    return getDelegateDescription().getComponentType();
  }

  @Override
  public Supplier<Unary<TypePackage>> getDeclaredPackage() {
    return getDelegateDescription().getDeclaredPackage();
  }

  protected abstract TypeDescription getDelegateDescription();

  @Override
  public Function<TypeInstance, Object> getIdentityToken() {
    return getDelegateDescription().getIdentityToken();
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return getDelegateDescription().getInheritanceDescription();
  }

  @Override
  public Function<TypeInstance, Supplier<Nary<TypeCategory>>> getCategoriesCalculator() {
    return getDelegateDescription().getCategoriesCalculator();
  }

  @Override
  public Function<TypeInstance, Supplier<TypeNames>> getNamesCalculator() {
    return getDelegateDescription().getNamesCalculator();
  }

  @Override
  public Supplier<Nary<Object>> getReflectionTypeSupplier() {
    return getDelegateDescription().getReflectionTypeSupplier();
  }

  @Override
  public Supplier<Nary<Class<?>>> getRuntimeClasses() {
    return getDelegateDescription().getRuntimeClasses();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getRuntimeType() {
    return getDelegateDescription().getRuntimeType();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return getDelegateDescription().getTypeArguments();
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return getDelegateDescription().getTypeConstructors();
  }

  @Override
  public Supplier<Nary<TypeField>> getTypeFields() {
    return getDelegateDescription().getTypeFields();
  }

  @Override
  public BiPredicate<TypeInstance, Object> getInstancePredicate() {
    return getDelegateDescription().getInstancePredicate();
  }

  @Override
  public Supplier<Nary<TypeMethod>> getTypeMethods() {
    return getDelegateDescription().getTypeMethods();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return getDelegateDescription().getTypeParametersSupplier();
  }

  @Override
  public boolean isForVariableType() {
    return getDelegateDescription().isForVariableType();
  }
}
