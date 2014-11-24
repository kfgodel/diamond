package ar.com.kfgodel.diamond.impl.equals;

import java.util.function.Function;

/**
 * This type represents the function to obtain equals structures for immutable types
 * Created by kfgodel on 23/11/14.
 */
public class ImmutableIdentityToken<T> implements Function<T, Object> {

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

    public static<T> ImmutableIdentityToken<T> create(Function<T, ?> tokenCalculator) {
        ImmutableIdentityToken structure = new ImmutableIdentityToken();
        structure.tokenCalculator = tokenCalculator;
        return structure;
    }

}
