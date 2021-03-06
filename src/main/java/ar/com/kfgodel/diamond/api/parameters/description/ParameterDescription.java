package ar.com.kfgodel.diamond.api.parameters.description;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents the diamond description of a executable parameter
 * Created by kfgodel on 07/11/14.
 */
public interface ParameterDescription {

  /**
   * @return The supplier to get the parameter declared type
   */
  Supplier<TypeInstance> getDeclaredType();

  /**
   * @return The supplier of the parameter name
   */
  Supplier<String> getName();

  /**
   * @return The supplier of the parameter modifiers
   */
  Supplier<? extends Nary<Modifier>> getModifiers();

  /**
   * @return The supplier of parameter annotations
   */
  Supplier<? extends Nary<Annotation>> getAnnotations();

  /**
   * @return The function to get the token that represents the instance identity
   */
  Function<ExecutableParameter, Object> getIdentityToken();
}
