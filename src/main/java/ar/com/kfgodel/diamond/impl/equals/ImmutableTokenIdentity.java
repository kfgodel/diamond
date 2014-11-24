package ar.com.kfgodel.diamond.impl.equals;

import java.util.function.Function;

/**
 * This type represents the function to obtain equals structures for immutable types
 * Created by kfgodel on 23/11/14.
 */
public class ImmutableTokenIdentity<T> implements Function<T, Object> {

    private Function<T, Object> tokenCalculator;
    private Object calculatedToken;


    @Override
    public Object apply(T typeInstance) {
        if(tokenCalculator != null){
            // First time
            calculatedToken = tokenCalculator.apply(typeInstance);
            tokenCalculator = null;
        }
        return calculatedToken;
    }

    public static<T> ImmutableTokenIdentity<T> create(Function<T, ?> structureCalculator) {
        ImmutableTokenIdentity structure = new ImmutableTokenIdentity();
        structure.tokenCalculator = structureCalculator;
        return structure;
    }

}
