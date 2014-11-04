package ar.com.kfgodel.streams;

import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier for stream taken from a collection.<br>
 *     Each time this supplier is called, a stream is generated from the collection
 * Created by kfgodel on 22/10/14.
 */
public class StreamFromCollectionSupplier<T> implements Supplier<Stream<T>> {

    private Supplier<? extends  Collection<T>> collection;

    @Override
    public Stream<T> get() {
        return collection.get().stream();
    }

    /**
     * Creates a stream supplier from the given collection
     * @param collection The collection to get the streams from
     * @param <T> The type of expected stream elements
     * @return The created supplier
     */
    public static<T> StreamFromCollectionSupplier<T> from(Collection<T> collection) {
        return using(CachedValue.eagerlyFrom(collection));
    }

    /**
     * Creates a stream supplier that will use a collection as the source<br>
     *     The collection will be obtained each time using the provided Supplier
     * @param collection The collection supplier to call each time a stream is needed
     * @param <T> The type of expected stream elements
     * @return The created stream supplier
     */
    public static<T> StreamFromCollectionSupplier<T> using(Supplier<? extends Collection<T>> collection) {
        StreamFromCollectionSupplier<T> supplier = new StreamFromCollectionSupplier<>();
        supplier.collection = collection;
        return supplier;
    }

    /**
     * Creates a stream supplier that will use the same collection as the source.<br>
     *     The collection is obtained the first time a stream is needed and then cached
     * @param supplier The collection supplier
     * @param <T> The type of expected stream elements
     * @return The created stream supplier
     */
    public static<T> StreamFromCollectionSupplier<T> lazilyBy(Supplier<? extends Collection<T>> supplier) {
        return using(CachedValue.lazilyBy(supplier));
    }
}
