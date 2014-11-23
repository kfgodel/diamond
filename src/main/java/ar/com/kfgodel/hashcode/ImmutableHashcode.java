package ar.com.kfgodel.hashcode;

import java.util.function.ToIntFunction;

/**
 * This type represents a calculated hashcode for an immutable instance that can be cached.<br>
 *     Because hashcode is so important performance-wise, this class is used instead of a CachedValue to avoid
 *     boxing an unboxing, though the behavior is very similar.
 *
 * Created by kfgodel on 23/11/14.
 */
public class ImmutableHashcode<T> implements ToIntFunction<T> {

    private int calculatedHashcode;
    private ToIntFunction<T> hashcodeCalculator;

    public static<T> ImmutableHashcode<T> create(ToIntFunction<T> realCalculator) {
        ImmutableHashcode immutableHashcode = new ImmutableHashcode();
        immutableHashcode.hashcodeCalculator = realCalculator;
        return immutableHashcode;
    }

    @Override
    public int applyAsInt(T value) {
        if(hashcodeCalculator != null){
            //This is the first time
            calculatedHashcode = hashcodeCalculator.applyAsInt(value);
            // Free the calculator reference
            hashcodeCalculator = null;
        }
        return calculatedHashcode;
    }
}
