package ar.com.kfgodel.diamond.api.exceptions;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents and executable behavior that can declare exceptions that are thrown
 * Created by kfgodel on 02/11/14.
 */
public interface Exceptionable {

  /**
   * The set of declared exceptions for this element
   *
   * @return The stream containing all the exceptions or empty if none declared
   */
  Nary<TypeInstance> declaredExceptions();
}
