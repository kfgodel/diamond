package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This type represents a description of a diamond type and serves as an abstraction of the underlying represented type
 * Created by kfgodel on 28/09/14.
 */
public interface TypeDescription {

  /**
   * @return The supplier of annotations of the described type
   */
  Supplier<Nary<Annotation>> getAnnotations();

  /**
   * @return The predicate to use when checking described type assignability
   */
  BiPredicate<TypeInstance, TypeInstance> getAssignabilityPredicate();

  /**
   * @return The supplier that can reproduce the bounds of the described type
   */
  Supplier<TypeBounds> getBounds();

  /**
   * @return The supplier used to define the type component
   */
  Supplier<Nary<TypeInstance>> getComponentType();

  /**
   * @return The supplier of the optional package for the type
   */
  Supplier<Nary<TypePackage>> getDeclaredPackage();

  /**
   * @return The function to get the compared structure for equality
   */
  Function<TypeInstance, Object> getIdentityToken();

  /**
   * @return The description of this type inheritance
   */
  InheritanceDescription getInheritanceDescription();

  /**
   * Describes the calculator that is needed, once the type is created, to get the categories
   * for the type
   * @return The function to get the supplier of categories for a given type
   */
  Function<TypeInstance,Supplier<Nary<TypeCategory>>> getCategoriesCalculator();

  /**
   * Creates a decription of the different names the type has.
   * @return The instance that describes all the names that can be calculated before the type exists
   */
  TypeNamesDescription getNamesDescription();

  /**
   * @return The supplier of the raw classes for the runtime description of the type
   */
  @Deprecated
  Supplier<Nary<Class<?>>> getRawClassesSupplier();

  /**
   * @return The lambda to get the reflection type for this description if it has one
   */
  Supplier<Nary<Object>> getReflectionTypeSupplier();

  /**
   * @return The supplier to get the classes used by the VM in runtime to
   * represent the types of objects of this type.<br>
   * It may be more than one due to upper bounds
   */
  Supplier<Nary<Class<?>>> getRuntimeClasses();

  /**
   * Because some types only exist at compile time and get replaced by actual classes at runtime,
   * this method describes which type is used in runtime for the type described.<br>
   * Normally you wil get the same type, except for type variables, wildcards, etc.
   * @return The supplier to get the {@link TypeInstance} that better represents an object
   * of this type in runtime.<br>
   */
  Supplier<Nary<TypeInstance>> getRuntimeType();

  /**
   * @return The supplier of type arguments
   */
  Supplier<Nary<TypeInstance>> getTypeArguments();

  /**
   * The set of constructors that will be part of this type creators
   *
   * @return A stream to gather all constructors
   */
  Supplier<Nary<TypeConstructor>> getTypeConstructors();

  /**
   * The set of fields that will be part of this type state
   *
   * @return A stream to gather all fields
   */
  Supplier<Nary<TypeField>> getTypeFields();

  /**
   * @return The predicate to test instances against the described type to verify its type
   */
  Predicate<Object> getTypeForPredicate();

  /**
   * The set of methods that will be part of this type behavior
   *
   * @return A stream to gather all methods
   */
  Supplier<Nary<TypeMethod>> getTypeMethods();

  /**
   * @return The supplier of type parameters for the described type
   */
  Supplier<Nary<TypeInstance>> getTypeParametersSupplier();

  /**
   * Indicates if this description is for a type that cannot be statically determined at compile type in every scope
   * (wildcards and type variables) and for that reason its class representation may be bounded to other type.
   *
   * @return true if this type represents a type variable, a wildcard or annotated version of them
   */
  boolean isForVariableType();
}
