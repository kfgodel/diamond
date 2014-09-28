package ar.com.kfgodel.diamond.impl.fragments;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.generics.SupertypeParameterization;

import java.util.List;
import java.util.function.Consumer;

/**
 * This type represents the argument replacer that from a subtype arguments and a supertype parameterization
 * knows how to replace the supertype arguments for their actual values
 * Created by kfgodel on 27/09/14.
 */
public class ActualArgumentReplacer implements Consumer<List<TypeInstance>> {
    private List<TypeInstance> subtypeArguments;
    private SupertypeParameterization parameterization;

    @Override
    public void accept(List<TypeInstance> typeInstances) {
    }

    public static ActualArgumentReplacer create(List<TypeInstance> subtypeArguments, SupertypeParameterization parameterization) {
        ActualArgumentReplacer replacer = new ActualArgumentReplacer();
        replacer.subtypeArguments = subtypeArguments;
        replacer.parameterization = parameterization;
        return replacer;
    }

}
