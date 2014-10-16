package ar.com.kfgodel.diamond.api.sources.constructors;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;

import java.lang.reflect.Constructor;

/**
 * This type represents the possible sources for a type constructor
 * Created by kfgodel on 15/10/14.
 */
public interface ConstructorSources {
    /**
     * Retrieves the constructor instance representation for the given native constructor
     * @param nativeConstructor The native representation
     * @return The diamond representation
     */
    TypeConstructor from(Constructor<?> nativeConstructor);

    /**
     * Retrieves the constructor instance representation from its description
     * @param constructorDescription The description to get an instance
     * @return The diamond representation for the description
     */
    TypeConstructor from(ConstructorDescription constructorDescription);
}
