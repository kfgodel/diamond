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
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.is.TypeTests;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;

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
  Nary<TypeInstance> componentType();

  /**
   * @return The information about this type constructors<br>
   * TypeConstructors holds the relationship between this type and constructors to create instances of this type
   */
  TypeConstructors constructors();

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
  Nary<TypePackage> declaredPackage();

  /**
   * @return The information about this type fields.<br>
   * TypeFields holds the relationship between this type and the state fields that instances of this type have
   */
  TypeFields fields();

  /**
   * @return The information about this type generification.<br>
   * TypeGenerics holds the relationships between this type and its parameters, arguments and bounds
   */
  TypeGenerics generics();

  /**
   * @return Sames as calling newInstance()
   */
  @Override
  Object get();

  /**
   * @return The information about this type inheritance.<br>
   * TypeInheritance holds the relationships between this type and its parent types
   */
  TypeInheritance inheritance();

  /**
   * Starts a verification test with this type
   * @return The api to verify assertions on this type
   */
  TypeTests is();

  /**
   * Indicates if the given object is assignable to this type.<br>
   * Or this type is part if the object's type hierarchy. This method follows the same
   * semantics as Class.isInstance().<br>
   * <br>
   * For upper bounded types with more than one bound this is true if any of the upper bounds is true.
   *
   * @param anObject The object to test
   * @return true if the given object can be casted to this type without an exception,
   * false if object is null or otherwise
   */
  boolean isTypeFor(Object anObject);

  /**
   * The set of kinds this type belongs to.<br> This set is usually defined on the language
   * specification
   *
   * @return The kinds for this type
   */
  Nary<Kind> kinds();

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
   * but they have no relationship. For that reason, {@link Object} is used on this method and must
   * be casted down to get the actual sub-type. Most of the times they are sub-types of {@link Type}.<br>
   * <br>
   * If this type has no native reflection representation then empty is returned
   *
   * @return The type instance used by reflection to represent this type natively
   */
  Nary<Object> reflectionType();

  /**
   * Returns the classes that represent this type in runtime.<br>
   * Objects that are instances of this type are created as instances of the given classes.<br>
   * <br>
   * Because wildcards and variables can have more than one upper bound the returned {@link Nary}
   * may contain more than one class.<br>
   * Types based on classes, parameterized types or arrays have only 1 runtime class.<br>
   * There could be types with no runtime classes at all<br>
   * <br>
   * @return The nary containing the class or classes for this type
   */
  Nary<Class<?>> runtimeClasses();

}
