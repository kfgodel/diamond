package ar.com.kfgodel.streams;

import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier for streams of one element.<br>
 *     It caches the reference to the element but produces a different stream each time it's called
 * Created by kfgodel on 22/10/14.
 */
public class StreamFromElementSupplier<T> implements Supplier<Stream<T>> {

    private LazyValue<T> element;

    @Override
    public Stream<T> get() {
        return Stream.of(element.get());
    }

    public static<T> StreamFromElementSupplier<T> from(T element) {
        return using(SuppliedValue.eagerlyFrom(element));
    }

    public static<T> StreamFromElementSupplier<T> using(Supplier<T> generator) {
        StreamFromElementSupplier<T> supplier = new StreamFromElementSupplier<>();
        supplier.element = SuppliedValue.lazilyBy(generator);
        return supplier;
    }

}
