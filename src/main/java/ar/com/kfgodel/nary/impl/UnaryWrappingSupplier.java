package ar.com.kfgodel.nary.impl;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents a supplier for streams of one element.<br>
 * It caches the reference to the element but produces a different stream each time it's called
 * Created by kfgodel on 22/10/14.
 */
public class UnaryWrappingSupplier<T> implements Supplier<Unary<T>> {

  private Supplier<T> supplier;

  @Override
  public Unary<T> get() {
    return Nary.of(supplier.get());
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
