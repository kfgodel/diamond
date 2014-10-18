package ar.com.kfgodel.diamond.impl.fields.equality;

import ar.com.kfgodel.diamond.api.fields.TypeField;

/**
 * This type represents the equality definition for fields
 * Created by kfgodel on 16/10/14.
 */
public class FieldEquality {

    public static final FieldEquality INSTANCE = new FieldEquality();


    /**
     * Compares fields by equality using the name and the storage type
     * @param one One field to compare
     * @param obj the other obejct
     * @return true if both are fields have same name and type
     */
    public boolean areEquals(TypeField one, Object obj){
        if(!(obj instanceof TypeField)){
            return false;
        }
        TypeField other = (TypeField) obj;
        if(!one.name().equals(other.name())){
            return false;
        }
        if(!one.declaringType().equals(other.declaringType())){
            return false;
        }
        boolean equalType = one.type().equals(other.type());
        return equalType;
    }
}
