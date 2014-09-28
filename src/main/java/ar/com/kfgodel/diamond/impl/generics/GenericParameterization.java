package ar.com.kfgodel.diamond.impl.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.generics.parameters.ValueSubstituter;
import ar.com.kfgodel.diamond.impl.generics.parameters.ValueSubstitution;

import java.util.List;

/**
 * This type represents the parameterization that a subtype does over its supertype.<br>
 *     The parameterization maps subtype parameters to supertype arguments, allowing you to
 *     get actual supertype arguments from subtype arguments
 *
 * Created by kfgodel on 27/09/14.
 */
public class GenericParameterization implements SupertypeParameterization {

    private ValueSubstituter<TypeInstance> argumentSubstituter;

    public static GenericParameterization create(List<ValueSubstitution> argumentSubstitutions) {
        GenericParameterization parameterization = new GenericParameterization();
        parameterization.argumentSubstituter = ValueSubstituter.create(argumentSubstitutions);
        return parameterization;
    }

    @Override
    public void parameterizeWith(List<TypeInstance> subtypeArguments, List<TypeInstance> supertypeArgs) {
        if(subtypeArguments.isEmpty()){
            // There are no arguments to replace from
            return;
        }
        this.argumentSubstituter.accept(subtypeArguments, supertypeArgs);
    }
}
