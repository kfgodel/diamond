package ar.com.kfgodel.diamond.api.parameters.description;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;

/**
 * This type represents the diamond description of a executable parameter
 * Created by kfgodel on 07/11/14.
 */
public interface ParameterDescription {

    /**
     * @return The supplier to get the parameter declared type
     */
    Supplier<TypeInstance> getDeclaredType();
}
