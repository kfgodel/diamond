package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.declaration.Declarable;
import ar.com.kfgodel.diamond.api.equals.TokenIdentifiable;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.api.generics.Generified;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.compile.CompileTimeHierarchy;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.is.TypeTests;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.api.types.runtime.TypeRuntime;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents a java type.<br>
 * Instances of this interface represent one of the possible types in Java language including type variables
 * and wildcards.<br>
 * <p>
 * Created by kfgodel on 20/09/14.
 */
public interface TypeInstance extends
  Named, Annotated, Supplier<Object>, Declarable, Generified, DiamondReflection, TokenIdentifiable {

  /**
   * @return The component type of this container type.<br>
   * Component type is only present on arrays that reify their component types
   */
  Unary<TypeInstance> componentType();

  /**
   * @return The information about this type constructors<br>
   * TypeConstructors holds the relationship between this type and constructors to create instances of this type
   */
  TypeConstructors constructors();

  /**
   * Allows access to this type runtime information.<br>
   * Because erasure is applied on compilation, a runtime representation of a type may be different than that of
   * the compile time.<br>
   *
   * @return THe information of this type representation in runtime
   */
  TypeRuntime runtime();

  /**
   * The name that identifies this type with its annotations and generics information.<br> This name could be used to declare
   * this exact type in source code including actual type arguments
   *
   * @return The name as a full type declaration (This is equivalent to the source code for this type declaration)
   */
  String declaration();

  /**
   * @return The package declared for this type, or empty if this type has none
   */
  Unary<TypePackage> declaredPackage();

  /**
   * @return The information about this type fields.<br>
   * TypeFields holds the relationship between this type and the state fields that instances of this type have
   */
  TypeFields fields();

  /**
   * @return The information about this type generification.<br>
   * TypeGenerics holds the relationships between this type and its parameters, arguments and bounds
   */
  TypeGenerics generic();

  /**
   * @return Sames as calling newInstance()
   */
  @Override
  Object get();

  /**
   * Allows access to this type hierarchy as defined in compile time (without erasure).<br>
   * Unlike standard reflection, this is theway you will probably think of the type's hierarchy
   * @return The information about this type hierarchy.<br>
   * CompileTimeHierarchy holds the relationships between this type and its parent types
   */
  CompileTimeHierarchy hierarchy();

  /**
   * Starts a verification test with this type
   * @return The api to verify assertions on this type
   */
  TypeTests is();

  /**
   * The set of known categories this type belongs to.<br> This set is usually defined by the language
   * specification
   *
   * @return The built-in categories for this type
   */
  Nary<TypeCategory> categories();

  /**
   * @return The nary of all the members (methods, fields and constructors)
   */
  Nary<TypeMember> members();

  /**
   * @return The information about this type methods.<br>
   * TypeMethods holds the relationship between this type and the methods that instances of this type understand
   */
  TypeMethods methods();

  /**
   * Returns the accessor object for this type names (in all their varieties)
   *
   * @return The source of type names for this instance
   */
  TypeNames names();

  /**
   * Creates a new instance of this type using the niladic constructor.<br>
   * If this type cannot be instantiated, or doesn't have a niladic constructor, and exception is thrown
   *
   * @return The newly created instance
   */
  Object newInstance();

  /**
   * This method returns the reflection object used to represent this type natively.<br>
   * Reflection uses {@link Type} or {@link java.lang.reflect.AnnotatedType} to represent types,
   * but they have no hierarchy relationship between them.<br>
   * For that reason, and to facilitate its usage, a class is used as parameter to indicate the expected
   * output type. If you don't know which type is best for this instance, then {@link Object} is the only
   * option. From there you can cast down as needed to get the actual sub-type.<br>
   * Usually you will encounter they are sub-types of {@link Type}.<br>
   * <br>
   * If this type has no native reflection representation then empty is returned (custom non-reflection types)
   *
   * @return The object used by reflection to represent this type natively in the language
   */
  <T> Unary<T> reflectedAs(Class<T> reflectionClass);

}
