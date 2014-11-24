package ar.com.kfgodel.diamond.impl.equals;

import ar.com.kfgodel.diamond.api.equals.EqualsStructure;

import java.util.function.Function;

/**
 * This type represents the function to obtain equals structures for immutable types
 * Created by kfgodel on 23/11/14.
 */
public class ImmutableEqualsStructure<T> implements Function<T, EqualsStructure> {

    private Function<T, EqualsStructure> structureCalculator;
    private EqualsStructure calculatedStructure;


    @Override
    public EqualsStructure apply(T typeInstance) {
        if(structureCalculator != null){
            // First time
            calculatedStructure = structureCalculator.apply(typeInstance);
            structureCalculator = null;
        }
        return calculatedStructure;
    }

    public static<T> ImmutableEqualsStructure<T> create(Function<T, EqualsStructure> structureCalculator) {
        ImmutableEqualsStructure structure = new ImmutableEqualsStructure();
        structure.structureCalculator = structureCalculator;
        return structure;
    }

}
