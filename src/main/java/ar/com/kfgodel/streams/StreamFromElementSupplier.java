package ar.com.kfgodel.streams;

import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier for streams of one element.<br>
 *     It caches the reference to the element but produces a different stream each time it's called
 * Created by kfgodel on 22/10/14.
 */
public class StreamFromElementSupplier<T> implements Supplier<Stream<T>> {

    private Supplier<T> element;

    @Override
    public Stream<T> get() {
        return Stream.of(element.get());
    }

    /**
     * Creates a stream supplier for a source element.<br>
     *     The created streams will only contain the given object
     * @param element The element to wrap in streams
     * @param <T> The expected stream element type
     * @return The created supplier
     */
    public static<T> StreamFromElementSupplier<T> from(T element) {
        return using(CachedValue.eagerlyFrom(element));
    }

    /**
     * Creates a stream supplier for a source element provided by a supplier.<br>
     *     The supplier will be called every time a stream is needed
     * @param generator The element supplier
     * @param <T> The expected stream element type
     * @return The created supplier
     */
    public static<T> StreamFromElementSupplier<T> using(Supplier<T> generator) {
        StreamFromElementSupplier<T> supplier = new StreamFromElementSupplier<>();
        supplier.element = generator;
        return supplier;
    }

    /**
     * Creates a stream supplier from one element, lazily defined by the given supplier.<br>
     *     The supplier will be called only the first time a stream is needed
     * @param supplier The element supplier
     * @param <T> The expected type of elements
     * @return The stream supplier
     */
    public static<T> StreamFromElementSupplier<T> lazilyBy(Supplier<T> supplier){
        return using(CachedValue.lazilyBy(supplier));
    }

}
