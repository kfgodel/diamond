package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.ClassMethod;

/**
 * This type represents the source of a class method source with the class defined
 * Created by kfgodel on 18/09/14.
 */
public interface ClassDefinedClassMethodSource {
    /**
     * Returns the class method that matches the given signature in the class of this source
     * @param methodName The name of the method
     * @param parameterTypes The method parameter types to match
     * @return The matching class method
     */
    ClassMethod identifiedAs(String methodName, Class<?>... parameterTypes);
}
