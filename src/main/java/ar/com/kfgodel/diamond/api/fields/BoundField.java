package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.members.BoundMember;

/**
 * This type represents a TypeField bound to a specific instance as 'this'
 * Created by kfgodel on 16/11/14.
 */
public interface BoundField extends BoundMember {

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
   *
   * @param value The value to set
   */
  void set(Object value);

  /**
   * Gets the current value of the bound field in the bound instance
   *
   * @return The field value for the instance
   */
  @Override
  Object get();
}