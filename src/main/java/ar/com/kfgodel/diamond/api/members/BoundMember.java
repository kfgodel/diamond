package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.naming.Named;

/**
 * This type represents a TypeMember bound to a specific instance
 * Created by kfgodel on 09/01/16.
 */
public interface BoundMember extends PolymorphicInvokable, Named {

  /**
   * @return The type member this instance is bound to
   */
  TypeMember typeMember();

  /**
   * @return The object to which the member is bound to
   */
  Object instance();

}
