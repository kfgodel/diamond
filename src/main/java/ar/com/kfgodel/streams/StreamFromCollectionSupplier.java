package ar.com.kfgodel.streams;

import ar.com.kfgodel.lazyvalue.api.LazyValue;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier for stream taken from a collection.<br>
 *     Each time this supplier is called, a stream is generated from the collection
 * Created by kfgodel on 22/10/14.
 */
public class StreamFromCollectionSupplier<T> implements Supplier<Stream<T>> {

    private LazyValue<Collection<T>> collection;

    @Override
    public Stream<T> get() {
        return collection.get().stream();
    }

    public static<T> StreamFromCollectionSupplier<T> using(LazyValue<Collection<T>> collection) {
        StreamFromCollectionSupplier<T> supplier = new StreamFromCollectionSupplier<>();
        supplier.collection = collection;
        return supplier;
    }

}
