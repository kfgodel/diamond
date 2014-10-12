package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a field defined for a type
 * Created by kfgodel on 18/09/14.
 */
public interface ClassField extends Named {

    /**
     * @return The name of the field
     */
    @Override
    String name();

    /**
     * @return The type of this field storage.<br>
     *     This corresponds to the type declared for this field
     */
    TypeInstance type();
}
