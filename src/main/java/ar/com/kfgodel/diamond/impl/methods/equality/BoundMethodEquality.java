package ar.com.kfgodel.diamond.impl.methods.equality;

import ar.com.kfgodel.diamond.api.methods.BoundMethod;

import java.util.Objects;

/**
 * This type represents the equality definition for bound fields
 * Created by kfgodel on 06/01/16.
 */
public class BoundMethodEquality {

    public static final BoundMethodEquality INSTANCE = new BoundMethodEquality();


    /**
     * Compares bound methods by equality using the bound instance and the bound type method
     * @param one One field to compare
     * @param obj the other field
     * @return true if both represent the same method and are bound to the same object
     */
    public boolean areEquals(BoundMethod one, Object obj){
        if(one == obj){
            return true;
        }
        if(one == null || obj == null || !(obj instanceof BoundMethod)){
            return false;
        }
        BoundMethod other = (BoundMethod) obj;
        return one.instance() == other.instance() && one.typeMethod().equals(other.typeMethod());
    }

    /**
     * Generates a hashcode of the given bound method using this equality criteria
     * @param boundField The method to hash
     * @return The hashcode based on the method and the instance
     */
    public int hash(BoundMethod boundField) {
        return Objects.hash(boundField.instance(), boundField.typeMethod());
    }
}
