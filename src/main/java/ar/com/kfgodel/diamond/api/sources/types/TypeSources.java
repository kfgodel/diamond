package ar.com.kfgodel.diamond.api.sources.types;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents the possible sources for a type instance
 * Created by kfgodel on 20/09/14.
 */
public interface TypeSources {

    /**
     * Retrieves a type instance from an unspecific type whose type is not known.<br>
     *     This method is needed to be able to treat AnnotatedType and Type with the same signature
     * @param nativeType The native object that represents a type (AnnotatedType or Type)
     * @return The type instance that represents the object
     * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException if the object doesn't represent a type
     */
    TypeInstance from(Object nativeType) throws DiamondException;

    /**
     * Retrieves a type instance from its description.<br>
     *     This method is the generic entry point for all type instance representations
     * @param description The diamond description for the type
     * @return The type representation
     */
    TypeInstance fromDescription(TypeDescription description);

    /**
     * Retrieves a type instance by its class name
     * @param typeName The complete class name
     * @return The type representation of the class
     * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException If the class name doesn't exists
     */
    TypeInstance named(String typeName) throws DiamondException;
}
