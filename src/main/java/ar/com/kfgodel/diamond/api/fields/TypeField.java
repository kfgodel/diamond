package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a field defined for a type that can store state
 * Created by kfgodel on 18/09/14.
 */
public interface TypeField extends Named, TypeMember {

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

    /**
     * Gets the value stored in the given object under the field represented by this instance
     * @param instance The object to take the value from
     * @param <R> The expected value type
     * @return The value obtained
     */
    <R> R getValueFrom(Object instance);

    /**
     * Sets the value in the given object under the field represented by this instance
     * @param instance The object to set the value into
     * @param value The value to set to
     */
    void setValueOn(Object instance, Object value);
}
