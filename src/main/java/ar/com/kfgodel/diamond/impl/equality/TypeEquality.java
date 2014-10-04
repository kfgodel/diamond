package ar.com.kfgodel.diamond.impl.equality;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents the reification of the equality concept for types
 * Created by kfgodel on 04/10/14.
 */
public class TypeEquality {

    public static final TypeEquality INSTANCE = new TypeEquality();

    public boolean areEquals(TypeInstance aType, Object other){
        return false;
    }
}
