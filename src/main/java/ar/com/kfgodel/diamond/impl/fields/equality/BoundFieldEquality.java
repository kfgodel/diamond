package ar.com.kfgodel.diamond.impl.fields.equality;

import ar.com.kfgodel.diamond.api.fields.BoundField;

import java.util.Objects;

/**
 * This type represents the equality definition for bound fields
 * Created by kfgodel on 06/01/16.
 */
public class BoundFieldEquality {

    public static final BoundFieldEquality INSTANCE = new BoundFieldEquality();


    /**
     * Compares bound fields by equality using the bound instance and the bound type field
     * @param one One field to compare
     * @param obj the other field
     * @return true if both represent the same field and are bound to the same object
     */
    public boolean areEquals(BoundField one, Object obj){
        if(one == obj){
            return true;
        }
        if(one == null || obj == null || !(obj instanceof BoundField)){
            return false;
        }
        BoundField other = (BoundField) obj;
        return one.instance() == other.instance() && one.typeField().equals(other.typeField());
    }

    /**
     * Generates a hashcode using the instance and method as equality criteria
     * @param boundField The field to hash
     * @return The hashcode compatible with this equality definition
     */
    public int hash(BoundField boundField) {
        return Objects.hash(boundField.instance(), boundField.typeField());
    }
}
