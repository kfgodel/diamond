package ar.com.kfgodel.diamond.impl.types.generics.parameters.parametrization;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.List;

/**
 * This type represents the parametrization that a subtype does over its supertype.<br>
 * The parametrization maps subtype parameters to supertype arguments, allowing you to
 * get actual supertype arguments from subtype arguments
 * <p>
 * Created by kfgodel on 27/09/14.
 */
public interface SupertypeParametrization {
  /**
   * Based on this instance definition, replaces supertype generic arguments with actual subtype arguments.<br>
   * This usually replaces a type variable with its type value
   *
   * @param subtypeArguments The subtype actual values
   * @param supertypeArgs    the supertype type variable arguments
   */
  void parameterizeWith(List<TypeInstance> subtypeArguments, List<TypeInstance> supertypeArgs);
}
