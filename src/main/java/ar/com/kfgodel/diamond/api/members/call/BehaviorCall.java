package ar.com.kfgodel.diamond.api.members.call;

import ar.com.kfgodel.diamond.api.members.TypeBehavior;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a behavior element (method or constructor) with arguments already bound.<br>
 * <p>
 * Created by kfgodel on 17/11/14.
 */
public interface BehaviorCall extends Function<Object, Object>, Supplier<Object>, Runnable, Consumer<Object> {
  /**
   * Invokes this behavior with the defined arguments on the given instance
   *
   * @param instance The instance to make the invocation on
   * @return The returned behavior result
   */
  <R> R invokeOn(Object instance);

  /**
   * Invokes this behavior with the implicit arguments without an instance (for static behavior)
   *
   * @return The returned result
   */
  <R> R invoke();

  /**
   * @return The behavior bound in this instance to the implicit arguments
   */
  TypeBehavior boundBehavior();

  /**
   * @return The implicit arguments of this call
   */
  Object[] arguments();
}
