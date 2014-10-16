package ar.com.kfgodel.diamond.api.sources.fields;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;

import java.lang.reflect.Field;

/**
 * This type represents the possibles sources to get a class field
 * Created by kfgodel on 18/09/14.
 */
public interface FieldSources {

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
    TypeField from(FieldDescription fieldDescription);
}
