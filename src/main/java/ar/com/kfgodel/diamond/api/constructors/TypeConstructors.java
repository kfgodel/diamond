package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the source of constructors for a given type
 * Created by kfgodel on 15/10/14.
 */
public interface TypeConstructors {
    /**
     * @return All the constructors for a type
     */
    Stream<TypeConstructor> all();

    /**
     * Returns the constructor that takes no arguments from this type
     * @return An optional with the niladic constructor or empty if this type doesn't have one
     */
    Optional<TypeConstructor> niladic();

    /**
     * Returns the constructor that matches the given param types
     * @param paramTypes The type of constructor arguments declared for the constructor
     * @return The constructor that matches the given types or empty optional
     */
    Optional<TypeConstructor> declaredFor(TypeInstance... paramTypes);

    /**
     * Returns the existing constructor that matches the given param types
     * @param paramTypes The type of constructor arguments declared for the constructor
     * @return The constructor that matches the given types
     * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException If no constructor matches the parameters
     */
    TypeConstructor existingDeclaredFor(TypeInstance... paramTypes) throws DiamondException;
}
