package ar.com.kfgodel.diamond.impl.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.List;

/**
 * This type represents the parameterization that a subtype does over its supertype.<br>
 *     The parameterization maps subtype parameters to supertype arguments, allowing you to
 *     get actual supertype arguments from subtype arguments
 *
 * Created by kfgodel on 27/09/14.
 */
public interface SupertypeParameterization {
    /**
     * Based on this instance definition, replaces supertype generic arguments with actual subtype arguments.<br>
     *     This usually replaces a type variable with its type value
     * @param subtypeArguments The subtype actual values
     * @param supertypeArgs the supertype type variable arguments
     */
    void parameterizeWith(List<TypeInstance> subtypeArguments, List<TypeInstance> supertypeArgs);
}
