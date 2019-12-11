package ar.com.kfgodel.nary.impl;

import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.SelfSupplier;
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
public class NarySupplierFromCollection<T> implements Supplier<Nary<T>> {

  private Supplier<? extends Collection<T>> collection;

  @Override
  public Nary<T> get() {
    return Nary.from(collection.get());
  }

  /**
   * Creates a stream supplier from the given collection
   *
   * @param collection The collection to get the streams from
   * @param <T>        The type of expected stream elements
   * @return The created supplier
   */
  public static <T> NarySupplierFromCollection<T> from(Collection<T> collection) {
    return using(SelfSupplier.of(collection));
  }

  /**
   * Creates a supplier from the given nary of elements that will be converted to
   * a collection and then cached to produce a new nary every time is asked
   *
   * @param elements The nary of the elements to comprise this supplier
   * @param <T>        The type of expected stream elements
   * @return The created supplier
   */
  public static <T> NarySupplierFromCollection<T> lazilyFrom(Stream<T> elements) {
    return lazilyBy(()-> elements.collect(Collectors.toList()));
  }

  /**
   * Creates a stream supplier that will use a collection as the source<br>
   * The collection will be obtained each time using the provided Supplier
   *
   * @param collection The collection supplier to call each time a stream is needed
   * @param <T>        The type of expected stream elements
   * @return The created stream supplier
   */
  public static <T> NarySupplierFromCollection<T> using(Supplier<? extends Collection<T>> collection) {
    NarySupplierFromCollection<T> supplier = new NarySupplierFromCollection<>();
    supplier.collection = collection;
    return supplier;
  }

  /**
   * Creates a stream supplier that will use the same collection as the source.<br>
   * The collection is obtained the first time a stream is needed and then cached
   *
   * @param supplier The collection supplier
   * @param <T>      The type of expected stream elements
   * @return The created stream supplier
   */
  public static <T> NarySupplierFromCollection<T> lazilyBy(Supplier<? extends Collection<T>> supplier) {
    Supplier<? extends Collection<T>> cachedValue = CachedValue.from(supplier);
    return using(cachedValue);
  }
}
