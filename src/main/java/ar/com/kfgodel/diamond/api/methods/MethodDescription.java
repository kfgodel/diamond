package ar.com.kfgodel.diamond.api.methods;

import java.util.function.Supplier;

/**
 * This type represents the description of a diamond method
 * Created by kfgodel on 07/10/14.
 */
public interface MethodDescription {

    /**
     * @return The supplier for getting the method name
     */
    Supplier<String> getName();
}
