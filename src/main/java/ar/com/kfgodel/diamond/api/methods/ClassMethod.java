package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents a method of a class.<br> Similar to Method
 * Created by kfgodel on 18/09/14.
 */
public interface ClassMethod extends Named {

    /**
     * @return The method name selector
     */
    @Override
    String name();

    /**
     * @return The type that represents the return type of this method.<br>
     *     The returned instance will be parametrized according to the type this method is declared on
     */
    TypeInstance returnType();

    /**
     * @return The type of parameters accepted by this method in the order they are required
     */
    Stream<TypeInstance> parameterTypes();

}
