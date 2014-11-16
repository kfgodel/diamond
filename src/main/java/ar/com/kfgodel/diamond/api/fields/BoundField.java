package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.naming.Named;

/**
 * This type represents a TypeField bound to a specific instance as 'this'
 * Created by kfgodel on 16/11/14.
 */
public interface BoundField extends PolymorphicInvokable, Named {

    /**
     * @return The type field this instance binds
     */
    TypeField typeField();

    /**
     * @return The object to which the field is bound to
     */
    Object instance();

    /**
     * Sets the given value in the bound field on the implicit instance
     * @param value The value to set
     */
    void set(Object value);

}