package ar.com.kfgodel.diamond.impl.types.generics.parameters.parametrization;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.substitutions.ValueSubstituter;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.substitutions.ValueSubstitution;

import java.util.List;

/**
 * This type represents the parameterization that a subtype does over its supertype.<br>
 *     The parameterization maps subtype parameters to supertype arguments, allowing you to
 *     get actual supertype arguments from subtype arguments
 *
 * Created by kfgodel on 27/09/14.
 */
public class GenericParametrization implements SupertypeParametrization {

    private ValueSubstituter<TypeInstance> argumentSubstituter;

    public static GenericParametrization create(List<ValueSubstitution> argumentSubstitutions) {
        GenericParametrization parameterization = new GenericParametrization();
        parameterization.argumentSubstituter = ValueSubstituter.create(argumentSubstitutions);
        return parameterization;
    }

    @Override
    public void parameterizeWith(List<TypeInstance> subtypeArguments, List<TypeInstance> supertypeArgs) {
        if(subtypeArguments.isEmpty()){
            // There are no arguments to replace from
            return;
        }
        // We replace generic parameters with actual generic arguments
        this.argumentSubstituter.accept(subtypeArguments, supertypeArgs);
    }
}
