package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.naming.NamedSource;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the source of fields for a given type
 * Created by kfgodel on 12/10/14.
 */
public interface TypeFields extends NamedSource<TypeField> {

  /**
   * @return All the class fields for a type
   */
  Nary<TypeField> all();

  /**
   * Retrieves this type fields that match the given name (including all of them, inherited and overlapping)
   *
   * @param fieldName The name for the searched fields
   * @return The stream of matching fields or an empty (if no match)
   */
  Nary<TypeField> named(String fieldName);

}
