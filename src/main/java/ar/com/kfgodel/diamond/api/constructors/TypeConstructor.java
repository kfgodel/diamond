package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.behavior.ArgumentBindable;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.members.TypeBehavior;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Constructor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents the a constructor defined for a type that creates new type instances
 * Created by kfgodel on 15/10/14.
 */
public interface TypeConstructor extends TypeMember, TypeBehavior, Supplier<Object>, Function<Object, Object>, Invokable, ArgumentBindable {


  /**
   * @return The name given to this constructor (usually the short name of its declaring type)
   */
  @Override
  String name();

  /**
   * @return The type of parameters accepted by this constructor in the order they are required
   */
  @Override
  Nary<TypeInstance> parameterTypes();

  /**
   * Invokes the constructor represented by this instance and returns the created object
   *
   * @param arguments The arguments needed to invoke this constructor
   * @return The created object
   */
  Object invoke(Object... arguments);

  /**
   * @return Sames as invoke() without arguments
   */
  @Override
  Object get();

  /**
   * Same as invoke(argument)
   *
   * @param argument
   * @return The created instance
   */
  @Override
  Object apply(Object argument);

  /**
   * @return The exceptions types declared by this constructor in its throws clause
   */
  @Override
  Nary<TypeInstance> declaredExceptions();

  /**
   * @return The type of instances created by this constructor. Same as the declaring type
   */
  @Override
  TypeInstance returnType();

  /**
   * Creates a behavior call of this constructor with the given arguments, making them implicit
   *
   * @param arguments The arguments to use when invoking this constructor in the method call
   * @return The created method call
   */
  BehaviorCall withArguments(Object... arguments);

  /**
   * @return The native representation of this constructor if any
   */
  Unary<Constructor> nativeType();
}
