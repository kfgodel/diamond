package ar.com.kfgodel.diamond.api.objects;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.fields.BoundFields;
import ar.com.kfgodel.diamond.api.methods.BoundMethods;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a meta-view of an object allowing to access information and operating on the object on a meta-level
 * Created by kfgodel on 17/11/14.
 */
public interface MetaObject extends DiamondReflection {

    /**
     * @return The instance that represents the type of this object
     */
    TypeInstance type();

    /**
     * @return An accessor for the methods bound to this meta-object instance
     */
    BoundMethods methods();

    /**
     * @return An accessor for the fields bound to this meta-object instance
     */
    BoundFields fields();

  /**
   * @return The instance of this object
   */
  Object instance();
}
