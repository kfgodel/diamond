package ar.com.kfgodel.diamond.impl.fields.equality;

import ar.com.kfgodel.diamond.api.fields.TypeField;

import java.util.stream.Stream;

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
        boolean matchesAllConditions = Stream.of(obj)
                .filter((object) -> object instanceof TypeField)
                .map(TypeField.class::cast)
                .filter((other) -> one.name().equals(other.name()))
                .filter((other) -> one.declaringType().equals(other.declaringType()))
                .filter((other) -> one.type().equals(other.type()))
                .count() == 1;
        return matchesAllConditions;
    }
}
