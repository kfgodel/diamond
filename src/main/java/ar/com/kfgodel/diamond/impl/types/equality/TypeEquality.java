package ar.com.kfgodel.diamond.impl.types.equality;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.Optional;

/**
 * This type represents the reification of the equality concept for types
 * Created by kfgodel on 04/10/14.
 */
public class TypeEquality {

    public static final TypeEquality INSTANCE = new TypeEquality();

    public boolean areEquals(TypeInstance aType, Object obj){
        if(!(obj instanceof TypeInstance)){
            return false;
        }
        TypeInstance other = (TypeInstance) obj;
        if(!aType.names().bareName().equals(other.names().bareName())){
            return false;
        }
        if (!equalComponent(aType, other)){
            return false;
        }
        if (!equalTypeArguments(aType, other)){
            return false;
        }
        if(!equalBounds(aType, other)){
            return false;
        }
        return true;
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
        return StreamEquality.INSTANCE.areEquals(aType.generics().typeArguments(), other.generics().typeArguments());
    }

    private boolean equalComponent(TypeInstance aType, TypeInstance other) {
        Optional<TypeInstance> aComponentType = aType.componentType();
        Optional<TypeInstance> otherComponentType = other.componentType();
        if(aComponentType.isPresent() != otherComponentType.isPresent()){
            return false;
        }
        Optional<Boolean> componentComparison = aComponentType.map((aComponent) -> aComponent.equals(otherComponentType.get()));
        if(componentComparison.isPresent() && !componentComparison.get()){
            return false;
        }
        return true;
    }
}
