package ar.com.kfgodel.diamond.impl.types.equality;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the reification of the equality concept for types
 * Created by kfgodel on 04/10/14.
 */
public class TypeEquality {

    public static final TypeEquality INSTANCE = new TypeEquality();

    public boolean areEquals(TypeInstance one, Object obj){
        boolean matchesAllConditions = Stream.of(obj)
                .filter((object) -> object instanceof TypeInstance)
                .map(TypeInstance.class::cast)
                .filter((other) -> one.names().bareName().equals(other.names().bareName()))
                .filter((other) -> equalComponent(one, other))
                .filter((other) -> equalTypeArguments(one, other))
                .filter((other) -> equalBounds(one, other))
                .count() == 1;
        return matchesAllConditions;
    }

    private boolean equalBounds(TypeInstance aType, TypeInstance other) {
        TypeBounds aTypeBounds = aType.generics().bounds();
        TypeBounds otherBounds = other.generics().bounds();
        if(!StreamEquality.INSTANCE.areEquals(aTypeBounds.upper(), otherBounds.upper())){
            return false;
        }
        return StreamEquality.INSTANCE.areEquals(aTypeBounds.lower(), otherBounds.lower());
    }

    private boolean equalTypeArguments(TypeInstance aType, TypeInstance other) {
        return StreamEquality.INSTANCE.areEquals(aType.generics().arguments(), other.generics().arguments());
    }

    private boolean equalComponent(TypeInstance aType, TypeInstance other) {
        Optional<TypeInstance> aComponentType = aType.componentType();
        Optional<TypeInstance> otherComponentType = other.componentType();
        if(aComponentType.isPresent() != otherComponentType.isPresent()){
            return false;
        }
        Optional<Boolean> componentComparison = aComponentType.map((aComponent) -> aComponent.equals(otherComponentType.get()));
        boolean hasDifferentComponentType = componentComparison.isPresent() && !componentComparison.get();
        return !hasDifferentComponentType;
    }
}
