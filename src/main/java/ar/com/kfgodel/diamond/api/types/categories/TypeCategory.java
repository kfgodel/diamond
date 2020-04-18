package ar.com.kfgodel.diamond.api.types.categories;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a category that groups types with similar features.<br>
 * This is used to relate types that have things in common, like boolean and {@link Boolean}.<br>
 * It can be extended to add custom categories
 *
 * Created by kfgodel on 03/02/15.
 */
public interface TypeCategory {

  /**
   * Indicates if the given type belong to this category (shares its properties)
   *
   * @param testedType The tested type
   * @return True if the type belongs to this category
   */
  boolean contains(TypeInstance testedType);

}
