package ar.com.kfgodel.diamond.api.equals;

/**
 * This type represents an object whose equality can be delegated to a token object that serves as the comparable identity.<br>
 * By using a token objects can abstract their complexity, and if they are immutable the token can be cached.<br>
 * To decide equality of two TokenIdentifiable objects, their tokens should be compared by equals
 * <p>
 * Created by kfgodel on 23/11/14.
 */
public interface TokenIdentifiable {

  /**
   * @return The object that serves as identity of this instance and can be compared by equals
   */
  Object getIdentityToken();
}
