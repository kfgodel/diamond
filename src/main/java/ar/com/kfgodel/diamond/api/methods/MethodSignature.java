package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.naming.Named;

import java.util.List;

/**
 * This type represents a signature for a method
 * Created by kfgodel on 19/09/14.
 */
public interface MethodSignature extends Named {

    /**
     * @return The method name selector
     */
    @Override
    String name();

    /**
     * @return The list of parameter of this signature
     */
    List<Class<?>> parameterTypes();
}
