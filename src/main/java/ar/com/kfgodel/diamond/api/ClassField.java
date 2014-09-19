package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.naming.Named;

/**
 * This type represents a field in a class.<br> Similar to Field
 * Created by kfgodel on 18/09/14.
 */
public interface ClassField extends Named {

    /**
     * @return The name of the field
     */
    @Override
    String name();
}
