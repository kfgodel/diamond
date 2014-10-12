package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.fields.ClassField;

/**
 * This type represents the source of a class field with the class already defined
 * Created by kfgodel on 18/09/14.
 */
public interface ClassDefinedClassFieldSource {
    /**
     * Returns the field named with the given name on the class defined
     * @param fieldName The name of the field
     * @return The classField found
     */
    ClassField named(String fieldName);
}
