package ar.com.kfgodel.diamond.api.sources.fields;

import ar.com.kfgodel.diamond.api.fields.ClassField;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;

import java.lang.reflect.Field;

/**
 * This type represents the possibles sources to get a class field
 * Created by kfgodel on 18/09/14.
 */
public interface ClassFieldSources {
    /**
     * Defines the class to obtain the field in
     * @param nativeClass The class instance that indicates the source
     * @return The partially defined field source
     */
    ClassDefinedClassFieldSource in(Class<?> nativeClass);

    /**
     * Retrieves the class field representation for the given field instance
     * @param fieldInstance the Field that identifies the class field
     * @return The class field representation
     */
    ClassField from(Field fieldInstance);

    /**
     * Retrieves the class field representation for the given description
     * @param fieldDescription The description of the field
     * @return The class field representation
     */
    ClassField from(FieldDescription fieldDescription);
}
