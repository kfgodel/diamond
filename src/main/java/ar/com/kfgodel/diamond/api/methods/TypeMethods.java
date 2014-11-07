package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.naming.NamedSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the source of class methods for a given type
 * Created by kfgodel on 18/09/14.
 */
public interface TypeMethods extends NamedSource<TypeMethod> {

    /**
     * @return All the class methods for the type
     */
    Nary<TypeMethod> all();

    /**
     * Retrieves this type methods that match the given name (including all of them, inherited and overloaded).<br>
     *     It may be 0, 1 or more
     * @param methodName The name for the searched methods
     * @return The nary of matching methods or an empty (if no match)
     */
    Nary<TypeMethod> named(String methodName);

    /**
     * Retrieves the methods of the type that matches name and parameter types (including inherited).<br>
     *     It may be 0, 1, or more
     * @param methodName The name of the method to look for
     * @param parameterTypes The method parameter types
     * @return The nary of matching methods
     */
    Nary<TypeMethod> withSignature(String methodName, TypeInstance... parameterTypes);
}
