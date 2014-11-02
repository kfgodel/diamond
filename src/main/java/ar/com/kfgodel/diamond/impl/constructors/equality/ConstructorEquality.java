package ar.com.kfgodel.diamond.impl.constructors.equality;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.hashcode.Hashcodes;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.stream.Stream;

/**
 * This type captures the concept of equality for TypeConstructor
 * Created by kfgodel on 16/10/14.
 */
public class ConstructorEquality {

    public static final ConstructorEquality INSTANCE = new ConstructorEquality();

    /**
     * Compares as constructor with other object to define equality.<br>
     *     Two constructors are equals if declared in same type and have same parameters
     * @param one The constructor to compare
     * @param obj The object to be compared with
     * @return true if they represent the same constructor
     */
    public boolean areEquals(TypeConstructor one, Object obj){
        boolean matchesAllConditions = Stream.of(obj)
                .filter((object) -> object instanceof TypeConstructor)
                .map(TypeConstructor.class::cast)
                .filter((other) -> one.declaringType().equals(other.declaringType()))
                .filter((other) -> StreamEquality.INSTANCE.areEquals(one.parameterTypes(), other.parameterTypes()))
                .count() == 1;
        return matchesAllConditions;
    }

    /**
     * Calculates the hashcode of a constructor to be consistent with equals definition
     * @param constructor The constructor to calculate its hashcode
     * @return The hash of its declaring type and parameter types
     */
    public int hashcodeFor(TypeConstructor constructor){
        return Hashcodes.joining(constructor.declaringType(), Hashcodes.forElementsIn(constructor.parameterTypes()));
    }


}
