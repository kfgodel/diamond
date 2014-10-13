package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;

/**
 * This type represents the description of a diamond field that can be used to get a {@link ClassField}
 * Created by kfgodel on 12/10/14.
 */
public interface FieldDescription {
    /**
     * @return The name to identify the field
     */
    Supplier<String> getName();

    /**
     * @return The type of the field storage
     */
    Supplier<TypeInstance> getType();
}
