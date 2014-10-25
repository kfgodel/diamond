package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.naming.NamedSource;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the source of class methods for a given type
 * Created by kfgodel on 18/09/14.
 */
public interface TypeMethods extends NamedSource<TypeMethod> {

    /**
     * @return All the class methods for a type
     */
    Stream<TypeMethod> all();

    /**
     * Retrieves this type methods that match the given name (including all of them, inherited and overloaded)
     * @param methodName The name for the searched methods
     * @return The stream of matching methods or an empty (if no match)
     */
    Stream<TypeMethod> named(String methodName);

    /**
     * Retrieves the unique type method that matches the given name.<br>
     *     If more than one method matches the name an exception is thrown
     * @param methodName The name to match methods
     * @return The optional with the found method, or empty
     * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException If more than one method with the given name
     */
    Optional<TypeMethod> uniqueNamed(String methodName) throws DiamondException;

    /**
     * Retrieves the unique existing method that matches the given name.<br>
     *     If no method exists then an exception is thrown
     * @param methodName The name to match with the method
     * @return The found method
     * @throws DiamondException If no method matches the given name
     */
    TypeMethod existingNamed(String methodName) throws DiamondException;

}
