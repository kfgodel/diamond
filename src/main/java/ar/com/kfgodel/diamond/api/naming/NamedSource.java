package ar.com.kfgodel.diamond.api.naming;

import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents a source of named objects
 * Created by kfgodel on 25/10/14.
 */
public interface NamedSource<N extends Named> {

  /**
   * Retrieves all the elements in this source that match the given name.<br>
   * The result may be 0, 1, or N
   *
   * @param elementName The name for the searched elements in this source
   * @return The stream of matching elements or an empty (if no match)
   */
  Nary<N> named(String elementName);

}
