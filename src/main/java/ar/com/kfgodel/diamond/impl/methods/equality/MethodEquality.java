package ar.com.kfgodel.diamond.impl.methods.equality;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.stream.Stream;

/**
 * This type represents the equality criteria to compare methods
 * Created by kfgodel on 16/10/14.
 */
public class MethodEquality {

    public static final MethodEquality INSTANCE = new MethodEquality();

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
}
