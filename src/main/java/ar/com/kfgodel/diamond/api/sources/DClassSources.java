package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.DClass;

/**
 * This type representes the possible sources to get a DClass instance
 * Created by kfgodel on 17/09/14.
 */
public interface DClassSources {
    /**
     * Retrieves the class representation from its bare class instance
     * @param objectClass The class instance
     * @return The class representation of the given class
     */
    DClass from(Class<?> objectClass);
}
