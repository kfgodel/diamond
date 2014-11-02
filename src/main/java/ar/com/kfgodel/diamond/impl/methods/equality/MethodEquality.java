package ar.com.kfgodel.diamond.impl.methods.equality;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.hashcode.Hashcodes;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.stream.Stream;

/**
 * This type represents the equality criteria to compare methods
 * Created by kfgodel on 16/10/14.
 */
public class MethodEquality {

    public static final MethodEquality INSTANCE = new MethodEquality();

    /**
     * Compares the given method with the object indicating if they are equals.<br>
     *     Are equals if have same name, parameters and declaring type
     * @param one The method to compare
     * @param obj The object to be compared with
     * @return true if represent the same method
     */
    public boolean areEquals(TypeMethod one, Object obj){
        boolean matchesAllConditions = Stream.of(obj)
                .filter((object) -> object instanceof TypeMethod)
                .map(TypeMethod.class::cast)
                .filter((other) -> one.name().equals(other.name()))
                .filter((other) -> one.declaringType().equals(other.declaringType()))
                .filter((other) -> StreamEquality.INSTANCE.areEquals(one.parameterTypes(), other.parameterTypes()))
                .count() == 1;
        return matchesAllConditions;
    }

    /**
     * Calculates the hashcode of a method to be consistent with equals definition
     * @param method The method to calculate its hashcode
     * @return The hash of its name, declaring type and parameter types
     */
    public int hashcodeFor(TypeMethod method){
        return Hashcodes.joining(method.name(), method.declaringType(), Hashcodes.forElementsIn(method.parameterTypes()));
    }

}
