package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;

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
    TypeConstructor fromDescription(ConstructorDescription constructorDescription);

    /**
     * Defines the class in which the constructor is going to be accessed
     * @param nativeClass The native representation
     * @return The constructor source with the type defined
     */
    TypeConstructors in(Class<?> nativeClass);
}
