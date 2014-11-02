package ar.com.kfgodel.diamond.impl.fields.equality;

import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.hashcode.Hashcodes;

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
     * @param obj the other object
     * @return true if both represent the same field
     */
    public boolean areEquals(TypeField one, Object obj){
        boolean matchesAllConditions = Stream.of(obj)
                .filter((object) -> object instanceof TypeField)
                .map(TypeField.class::cast)
                .filter((other) -> one.name().equals(other.name()))
                .filter((other) -> one.declaringType().equals(other.declaringType()))
                .count() == 1;
        return matchesAllConditions;
    }

    /**
     * Calculates the hashcode of a field to be consistent with equals definition
     * @param field The field to calculate its hashcode
     * @return The hash of its name and declaring type
     */
    public int hashcodeFor(TypeField field){
        return Hashcodes.joining(field.name(), field.declaringType());
    }
}
