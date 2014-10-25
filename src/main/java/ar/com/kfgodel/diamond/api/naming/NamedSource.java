package ar.com.kfgodel.diamond.api.naming;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents a source of named objects
 * Created by kfgodel on 25/10/14.
 */
public interface NamedSource<N extends Named> {

    /**
     * Retrieves this all the elements in this source that match the given name
     * @param elementName The name for the searched elements in this source
     * @return The stream of matching elements or an empty (if no match)
     */
    Stream<N> named(String elementName);

    /**
     * Retrieves the unique optional element that matches the given name in this source.<br>
     *     If more than one element matches the name an exception is thrown
     * @param elementName The name to match elements
     * @return The optional with the found element, or empty
     * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException If more than one element matches the name
     */
    Optional<N> uniqueNamed(String elementName) throws DiamondException;

    /**
     * Retrieves the unique existing element that matches the given name.<br>
     *     If no element exists then an exception is thrown
     * @param elementName The name to match the element
     * @return The found element
     * @throws DiamondException If no element matches
     */
    N existingNamed(String elementName) throws DiamondException;
}
