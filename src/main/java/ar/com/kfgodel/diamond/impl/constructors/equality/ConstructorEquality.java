package ar.com.kfgodel.diamond.impl.constructors.equality;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.streams.StreamEquality;

/**
 * This type captures the concept of equality for TypeConstructor
 * Created by kfgodel on 16/10/14.
 */
public class ConstructorEquality {

    public static final ConstructorEquality INSTANCE = new ConstructorEquality();

    public boolean areEquals(TypeConstructor first, Object obj){
        if(!(obj instanceof TypeConstructor)){
            return false;
        }
        TypeConstructor other = (TypeConstructor) obj;
        return StreamEquality.INSTANCE.areEquals(first.parameterTypes(), other.parameterTypes());
    }

}
