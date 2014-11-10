package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;

import java.lang.reflect.Field;

/**
 * This type represents the possibles sources to get a class field
 * Created by kfgodel on 18/09/14.
 */
public interface FieldSources {

    /**
     * Defines the class in which the field is going to be accessed
     * @param objectClass The instance that represents the class
     * @return The partially defined source
     */
    TypeFields in(Class<?> objectClass);


    /**
     * Retrieves the class field representation for the given field instance
     * @param fieldInstance the Field that identifies the class field
     * @return The class field representation
     */
    TypeField from(Field fieldInstance);

    /**
     * Retrieves the class field representation for the given description
     * @param fieldDescription The description of the field
     * @return The class field representation
     */
    TypeField fromDescription(FieldDescription fieldDescription);
}
