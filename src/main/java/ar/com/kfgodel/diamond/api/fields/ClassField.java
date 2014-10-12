package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.naming.Named;

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
}
