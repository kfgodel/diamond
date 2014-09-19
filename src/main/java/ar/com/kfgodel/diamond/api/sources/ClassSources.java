package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.ClassInstance;

/**
 * This type representes the possible sources to get a DClass instance
 * Created by kfgodel on 17/09/14.
 */
public interface ClassSources {
    /**
     * Retrieves the class representation in its bare class instance
     * @param classInstance The class instance
     * @return The class representation of the given class
     */
    ClassInstance from(Class<?> classInstance);

    /**
     * Retrieves the class representation of a class by its complete name (package name + class name).<br>
     *     As you would do it with Class::forName()
     * @param canonicalClassName The name that uniquely identifies the class
     * @return The class representation
     */
    ClassInstance named(String canonicalClassName);
}
