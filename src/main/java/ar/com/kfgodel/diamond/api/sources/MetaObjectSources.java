package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.objects.MetaObject;

/**
 * This type represents the source of meta objects
 * Created by kfgodel on 17/11/14.
 */
public interface MetaObjectSources {

  /**
   * Retrieves the meta-level representation of the given instance
   *
   * @param instance The object to retrieve the meta-representation
   * @return The representation of the object in a meta-level
   */
  MetaObject from(Object instance);
}
