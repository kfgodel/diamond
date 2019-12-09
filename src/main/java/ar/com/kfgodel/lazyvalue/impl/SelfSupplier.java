package ar.com.kfgodel.lazyvalue.impl;

import com.google.common.base.MoreObjects;

import java.util.function.Supplier;

/**
 * This class represents the implicit supplier a value is on itself.<br>
 *   Every object can be seen as a supplier of itself
 * Date: 9/12/19 - 17:44
 */
public class SelfSupplier<T> implements Supplier<T> {

  private T value;

  @Override
  public T get() {
    return value;
  }

  /**
   * Creates a supplier whose value is eagerly defined with the given parameter.<br>
   *   No calculation is needed to get the value for the created supplier. Always
   *   returns the same instance
   *
   * @param value The value to return each time the supplier is called
   * @param <T>   The expected type of value
   * @return The created supplier
   */
  public static <T> SelfSupplier<T> of(T value) {
    SelfSupplier supplier = new SelfSupplier();
    supplier.value = value;
    return supplier;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("value", value)
      .toString();
  }

}
