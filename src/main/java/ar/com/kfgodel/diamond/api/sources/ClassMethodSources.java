package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.ClassMethod;

import java.lang.reflect.Method;

/**
 * This type represents the possible sources of a class method
 * Created by kfgodel on 18/09/14.
 */
public interface ClassMethodSources {

    /**
     * Defines the class in which the method is going to be accessed
     * @param objectClass The instance that represents the class
     * @return The partially defined source
     */
    TypeMethodSource in(Class<?> objectClass);

    /**
     * Retrieves the diamond representation of the given method
     * @param methodInstance The instances that represents a method
     * @return The class method
     */
    ClassMethod from(Method methodInstance);
}
