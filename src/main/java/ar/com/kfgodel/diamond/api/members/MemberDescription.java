package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * This type represents the common type member description elements
 * Created by kfgodel on 01/11/14.
 */
public interface MemberDescription {

  /**
   * @return The supplier of the type that declares this member
   */
  Supplier<TypeInstance> getDeclaringType();

  /**
   * @return The supplier of modifiers applied to the member
   */
  Supplier<? extends Nary<Modifier>> getModifiers();

  /**
   * @return The supplier of the member invoker function
   */
  Supplier<PolymorphicInvokable> getInvoker();

  /**
   * @return The supplier to get the member name
   */
  Supplier<String> getName();

  /**
   * @return The supplier for this member's annotations
   */
  Supplier<? extends Nary<Annotation>> getAnnotations();

  /**
   * @return The supplier of member's generics information
   */
  Supplier<Generics> getGenerics();

  /**
   * @return the supplier of declared exceptions for this member
   */
  Supplier<? extends Nary<TypeInstance>> getDeclaredExceptions();

  /**
   * The supplier of parameters of the described member
   *
   * @return The parameters supplier
   */
  Supplier<? extends Nary<ExecutableParameter>> getParameters();

}
