package ar.com.kfgodel.diamond.api.parameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a parameter of an executable type (a method or constructor, lambda, etc)
 *
 * Created by kfgodel on 07/11/14.
 */
public interface ExecutableParameter {

    /**
     * The type declared for this parameter
     * @return The type representation of the expected argument for this parameter
     */
    TypeInstance declaredType();
}
