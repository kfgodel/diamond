package ar.com.kfgodel.nary.impl;

import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a supplier for streams of one element.<br>
 * It caches the reference to the element but produces a different stream each time it's called
 * Created by kfgodel on 22/10/14.
 */
public class NaryWrappingSupplier<T> implements Supplier<Nary<T>> {

  private Supplier<T> element;

  @Override
  public Nary<T> get() {
    return Nary.of(element.get());
  }

  /**
   * Creates a stream supplier for a source element provided by a supplier.<br>
   * The supplier will be called every time a stream is needed
   *
   * @param generator The element supplier
   * @param <T>       The expected stream element type
   * @return The created supplier
   */
  public static <T> NaryWrappingSupplier<T> of(Supplier<T> generator) {
    NaryWrappingSupplier<T> supplier = new NaryWrappingSupplier<>();
    supplier.element = generator;
    return supplier;
  }


}
