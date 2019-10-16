package ar.com.kfgodel.lazyvalue.api;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a value that is defined the first time is needed
 * Created by kfgodel on 21/09/14.
 */
public interface LazyValue<T> extends Supplier<T> {
  /**
   * Indicates if this value has already been defined
   *
   * @return True if the generator expression was evaluated and the value defined
   */
  boolean isAlreadyDefined();

  /**
   * @return The generator lambda that is going to be used to define the value of this instance.<br>
   * En empty optional is returned if the value is already defined and the lambda discarded
   */
  Optional<Supplier<T>> generator();

  /**
   * @return THe optional value if defined, an empty optional if not
   */
  Optional<T> getIfDefined();
}
