package ar.com.kfgodel.diamond.impl.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.List;

/**
 * This type represents  type parameterization were no relation to subtype is involved, thus
 * all original supertype arguments are preserved
 * Created by kfgodel on 27/09/14.
 */
public class NoParameterization implements SupertypeParameterization {

    public static final NoParameterization INSTANCE = new NoParameterization();

    @Override
    public void parameterizeWith(List<TypeInstance> subtypeArguments, List<TypeInstance> supertypeArgs) {
        // No change needed
    }
}
