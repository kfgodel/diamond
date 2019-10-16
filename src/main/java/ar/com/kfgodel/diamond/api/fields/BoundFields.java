package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.naming.NamedSource;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the source of bound fields for a meta-object
 * Created by kfgodel on 17/11/14.
 */
public interface BoundFields extends NamedSource<BoundField> {

  /**
   * @return All the fields bound to the instance
   */
  Nary<BoundField> all();

  /**
   * Retrieves the instance fields that match the given name (including all of them, inherited and overlapping)
   *
   * @param fieldName The name for the searched fields
   * @return The stream of matching fields or an empty (if no match)
   */
  Nary<BoundField> named(String fieldName);

}
