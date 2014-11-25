package ar.com.kfgodel.diamond.impl.fields.equality;

import ar.com.kfgodel.diamond.api.equals.CompositeIdentityToken;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.equals.ImmutableIdentityParts;

/**
 * This type represents the equality definition for fields
 * Created by kfgodel on 16/10/14.
 */
public class FieldEquality {

    public static final FieldEquality INSTANCE = new FieldEquality();


    /**
     * Compares fields by equality using the name and the storage type
     * @param one One field to compare
     * @param obj the other object
     * @return true if both represent the same field
     */
    public boolean areEquals(TypeField one, Object obj){
        if(one == obj){
            return true;
        }
        if(one == null || obj == null || !(obj instanceof TypeField)){
            return false;
        }
        TypeField other = (TypeField) obj;
        return one.getIdentityToken().equals(other.getIdentityToken());
    }

    /**
     * Generates the identity token of the given field
     * @param field The field to compare
     * @return The token that serves as a comparator element
     */
    public CompositeIdentityToken calculateTokenFor(TypeField field) {
        return ImmutableIdentityParts.create(
                field.name(),
                field.declaringType());
    }


}
