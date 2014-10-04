package ar.com.kfgodel.diamond.impl.equality;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

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
        if(!aType.names().canonicalName().equals(other.names().canonicalName())){
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
        TypeBounds aTypeBounds = aType.bounds();
        TypeBounds otherBounds = other.bounds();
        if(!equalTypes(aTypeBounds.upper(), otherBounds.upper())){
            return false;
        }
        return equalTypes(aTypeBounds.lower(), otherBounds.lower());
    }

    private boolean equalTypeArguments(TypeInstance aType, TypeInstance other) {
        return equalTypes(aType.typeArguments(), other.typeArguments());
    }

    private boolean equalTypes(Stream<TypeInstance> aTypeStream, Stream<TypeInstance> otherTypeStream) {
        Iterator<TypeInstance> aTypeArgumentsIterator = aTypeStream.iterator();
        Iterator<TypeInstance> otherTypeArgumentsIterator = otherTypeStream.iterator();
        while(aTypeArgumentsIterator.hasNext() && otherTypeArgumentsIterator.hasNext()){
            TypeInstance aTypeArgument = aTypeArgumentsIterator.next();
            TypeInstance otherTypeArgument = otherTypeArgumentsIterator.next();
            if(!aTypeArgument.equals(otherTypeArgument)){
                return false;
            }
        }
        if(aTypeArgumentsIterator.hasNext() != otherTypeArgumentsIterator.hasNext()){
            return false;
        }
        return true;
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
