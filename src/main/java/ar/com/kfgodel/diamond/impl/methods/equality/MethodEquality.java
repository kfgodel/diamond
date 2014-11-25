package ar.com.kfgodel.diamond.impl.methods.equality;

import ar.com.kfgodel.diamond.api.equals.CompositeIdentityToken;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.equals.ImmutableIdentityParts;

import java.util.stream.Collectors;

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
        if(one == obj){
            return true;
        }
        if(one == null || obj == null || !(obj instanceof TypeMethod)){
            return false;
        }
        TypeMethod other = (TypeMethod) obj;
        return one.getIdentityToken().equals(other.getIdentityToken());
    }

    /**
     * Creates the method token identifier
     * @param method The method to represent
     * @return The composite token
     */
    public CompositeIdentityToken calculateTokenFor(TypeMethod method) {
        return ImmutableIdentityParts.create(
                method.name(),
                method.declaringType(),
                ImmutableIdentityParts.create(method.parameterTypes().collect(Collectors.toList())));
    }

}
