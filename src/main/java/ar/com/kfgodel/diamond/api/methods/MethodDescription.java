package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents the description of a diamond method to get a {@link TypeMethod}
 * Created by kfgodel on 07/10/14.
 */
public interface MethodDescription extends MemberDescription {

  /**
   * @return The supplier for the return type of the described method
   */
  Supplier<TypeInstance> getReturnType();

  /**
   * @return The supplier of the method's default value
   */
  Supplier<Unary<Object>> getDefaultValue();

  /**
   * @return The supplier of the native representation
   */
  Supplier<Unary<Method>> getNativeMethod();

  /**
   * @return The function to get the token that represents the instance identity
   */
  Function<TypeMethod, Object> getIdentityToken();
}
