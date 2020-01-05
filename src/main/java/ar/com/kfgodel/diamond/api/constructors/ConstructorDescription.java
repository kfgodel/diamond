package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Constructor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents the Diamond description of a constructor abstracting the underlying implementation
 * Created by kfgodel on 15/10/14.
 */
public interface ConstructorDescription extends MemberDescription {

  /**
   * @return tHte supplier of the constructor native representation
   */
  Supplier<Unary<Constructor>> getNativeConstructor();

  /**
   * @return A token that represents this constructor and can be compared with other constructor tokens
   */
  Function<TypeConstructor, Object> getIdentityToken();
}
