package ar.com.kfgodel.lazyvalue.impl;

import ar.com.kfgodel.nary.api.Nary;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents a supplier for streams taken from a collection.<br>
 * Each time this supplier is called, a stream is generated from the collection
 * Created by kfgodel on 22/10/14.
 */
public class CachedValues<T> implements Supplier<Nary<T>> {

  private Supplier<? extends Collection<T>> collectionSupplier;

  @Override
  public Nary<T> get() {
    final Collection<T> collection = collectionSupplier.get();
    return Nary.from(collection);
  }

  /**
   * Creates a supplier from the given nary of elements that will be converted to
   * a collection and then cached to produce a new nary every time is asked
   *
   * @param elements The nary of the elements to comprise this supplier
   * @param <T>        The type of expected stream elements
   * @return The created supplier
   */
  public static <T> CachedValues<T> from(Stream<T> elements) {
    return adapting(()-> elements);
  }

  /**
   * Creates a supplier from the given supplier stream that will be used to cached
   * the elements in a collection and used to generate future {@link Nary} when called
   *
   * @param elements The nary of the elements to comprise this supplier
   * @param <T>        The type of expected stream elements
   * @return The created supplier
   */
  public static <T> CachedValues<T> adapting(Supplier<? extends Stream<T>> elements) {
    return from(()-> {
      return elements.get()
        .collect(Collectors.toList());
    });
  }

  /**
   * Creates a supplier of nary that will use a collection to get the elements<br>
   * The collection is lazily obtained the first time and cached for subsequent calls
   *
   * @param collection The supplier to get the collection the first time
   * @param <T>        The type of expected stream elements
   * @return The created stream supplier that can be reused
   */
  public static <T> CachedValues<T> from(Supplier<? extends Collection<T>> collection) {
    CachedValues<T> supplier = new CachedValues<>();
    supplier.collectionSupplier = CachedValue.from(collection);
    return supplier;
  }

}
