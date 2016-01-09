package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

/**
 * This type serves as a mark to all the functional adapters.<br>
 *   It's function is internal for implementation purposes
 * Created by kfgodel on 09/01/16.
 */
public interface PolyAdapter extends PolymorphicInvokable {
  /**
   * @return The code that this adapter adapts
   */
  Object adaptedCode();
}
