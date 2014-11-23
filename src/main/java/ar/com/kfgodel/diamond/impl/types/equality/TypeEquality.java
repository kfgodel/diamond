package ar.com.kfgodel.diamond.impl.types.equality;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.hashcode.Hashcodes;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.stream.Stream;

/**
 * This type represents the reification of the equality concept for types
 * Created by kfgodel on 04/10/14.
 */
public class TypeEquality {

    public static final TypeEquality INSTANCE = new TypeEquality();

    public boolean areEquals(TypeInstance one, Object obj){
        if(one == obj){
            return true;
        }
        if(one == null || obj == null || !(obj instanceof TypeInstance) || one.hashCode() != obj.hashCode()){
            return false;
        }
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

    /**
     * Calculates the hashcode of a type to be consistent with equals definition
     * @param type The type to calculate its hashcode
     * @return The hash of its name, component type, type arguments, and bounds
     */
    public int hashcodeFor(TypeInstance type){
        return Hashcodes.joining(
                type.names().bareName(),
                Hashcodes.forElementsIn(type.componentType()),
                Hashcodes.forElementsIn(type.generics().arguments()),
                Hashcodes.forElementsIn(type.generics().bounds().lower()),
                Hashcodes.forElementsIn(type.generics().bounds().upper())
                );
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
        Nary<TypeInstance> aComponentType = aType.componentType();
        Nary<TypeInstance> otherComponentType = other.componentType();
        if(aComponentType.isPresent() != otherComponentType.isPresent()){
            return false;
        }
        ar.com.kfgodel.optionals.Optional<Boolean> componentComparison = aComponentType.mapOptional((aComponent) -> aComponent.equals(otherComponentType.get()));
        boolean hasDifferentComponentType = componentComparison.isPresent() && !componentComparison.get();
        return !hasDifferentComponentType;
    }
}
