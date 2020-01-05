package ar.com.kfgodel.nary.impl;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents a supplier of a value wrapped in a {@link Unary} that is created each time
 * it is invoked.<br>
 * If the value is null it will be assumed to represent the expected empty {@link Nary}.
 *
 * Created by kfgodel on 22/10/14.
 */
public class UnaryWrappingSupplier<T> implements Supplier<Unary<T>> {

  private Supplier<T> supplier;

  @Override
  public Unary<T> get() {
    final T nullableElement = supplier.get();
    return Nary.of(nullableElement);
  }

  /**
   * Creates a stream supplier for a source element provided by a supplier.<br>
   * The supplier will be called every time a stream is needed
   *
   * @param generator The element supplier
   * @param <T>       The expected stream element type
   * @return The created supplier
   */
  public static <T> UnaryWrappingSupplier<T> of(Supplier<T> generator) {
    UnaryWrappingSupplier<T> supplier = new UnaryWrappingSupplier<>();
    supplier.supplier = generator;
    return supplier;
  }


}
