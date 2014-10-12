package ar.com.kfgodel.diamond.api.fields;

import java.util.function.Supplier;

/**
 * This type represents the description of a diamond field
 * Created by kfgodel on 12/10/14.
 */
public interface FieldDescription {
    /**
     * @return The name to identify the field
     */
    Supplier<String> getName();
}
