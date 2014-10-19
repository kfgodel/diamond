package ar.com.kfgodel.diamond.impl.constructors.equality;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.stream.Stream;

/**
 * This type captures the concept of equality for TypeConstructor
 * Created by kfgodel on 16/10/14.
 */
public class ConstructorEquality {

    public static final ConstructorEquality INSTANCE = new ConstructorEquality();

    public boolean areEquals(TypeConstructor one, Object obj){
        boolean matchesAllConditions = Stream.of(obj)
                .filter((object) -> object instanceof TypeConstructor)
                .map(TypeConstructor.class::cast)
                .filter((other) -> one.declaringType().equals(other.declaringType()))
                .filter((other) -> StreamEquality.INSTANCE.areEquals(one.parameterTypes(), other.parameterTypes()))
                .count() == 1;
        return matchesAllConditions;
    }

}
