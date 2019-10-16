package ar.com.kfgodel.diamond.api.cache;

import ar.com.kfgodel.diamond.api.DiamondReflection;

import java.util.function.Supplier;

/**
 * This type represents the cache used by diamond to reuse commonly accessed instances
 * Created by kfgodel on 09/11/14.
 */
public interface DiamondCache {

  /**
   * Invalidates the cache and releases every reference to reused diamond instances.<br>
   * New instances will be created for diamond representations
   */
  <T extends DiamondReflection> void invalidate();

  /**
   * Tries to reuse existing representation for a native reflection object, or creates a new one using the given
   * supplier.<br>
   * If there's a previous instance for the given native instance, then that is used
   *
   * @param nativeReflection       The native reflection instance that represents a reflective element
   * @param representationSupplier The supplier to use if there's no cached diamond representation for the given reflection object
   * @param <T>                    the expected diamond type
   * @return The diamond representation for the given reflection object
   */
  <T> T reuseOrCreateRepresentationFor(Object nativeReflection, Supplier<T> representationSupplier);
}
