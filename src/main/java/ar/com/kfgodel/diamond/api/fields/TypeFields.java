package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.naming.NamedSource;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the source of fields for a given type
 * Created by kfgodel on 12/10/14.
 */
public interface TypeFields extends NamedSource<TypeField> {

    /**
     * @return All the class fields for a type
     */
    Stream<TypeField> all();

    /**
     * Retrieves this type fields that match the given name (including all of them, inherited and overlapping)
     * @param fieldName The name for the searched fields
     * @return The stream of matching fields or an empty (if no match)
     */
    Stream<TypeField> named(String fieldName);

    /**
     * Retrieves the unique type field that matches the given name.<br>
     *     If more than one field matches the name an exception is thrown
     * @param fieldName The name to match fields
     * @return The optional with the found field, or empty
     * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException If more than one field with the name
     */
    Optional<TypeField> uniqueNamed(String fieldName) throws DiamondException;

    /**
     * Retrieves the unique existing field that matches the given name.<br>
     *     If no field exists then an exception is thrown
     * @param fieldName The name to match with the filed
     * @return The found field
     * @throws DiamondException If no field matches
     */
    TypeField existingNamed(String fieldName) throws DiamondException;
}
