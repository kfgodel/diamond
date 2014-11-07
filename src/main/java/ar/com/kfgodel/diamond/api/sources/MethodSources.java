package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;

import java.lang.reflect.Method;

/**
 * This type represents the possible sources of a class method
 * Created by kfgodel on 18/09/14.
 */
public interface MethodSources {

    /**
     * Defines the class in which the method is going to be accessed
     * @param objectClass The instance that represents the class
     * @return The partially defined source
     */
    TypeMethods in(Class<?> objectClass);

    /**
     * Retrieves the diamond representation of the given method
     * @param methodInstance The instances that represents a method
     * @return The class method
     */
    TypeMethod from(Method methodInstance);

    /**
     * Retrieves the diamond representation of a class method from its description
     * @param methodDescription The method description
     * @return The class methods
     */
    TypeMethod from(MethodDescription methodDescription);
}
