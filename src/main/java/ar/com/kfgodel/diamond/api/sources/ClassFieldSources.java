package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.ClassField;

import java.lang.reflect.Field;

/**
 * This type represents the possibles sources to get a class field
 * Created by kfgodel on 18/09/14.
 */
public interface ClassFieldSources {
    /**
     * Defines the class to obtain the field in
     * @param classInstance The class instance that indicates the source
     * @return The partially defined field source
     */
    ClassDefinedClassFieldSource in(Class<?> classInstance);

    /**
     * Retrieves the class field representation for the given field isntance
     * @param fieldInstance the Field that identifies the class field
     * @return The class field representation
     */
    ClassField from(Field fieldInstance);
}
