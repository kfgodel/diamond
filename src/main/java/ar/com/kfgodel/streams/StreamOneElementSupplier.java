package ar.com.kfgodel.streams;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier for streams of one element.<br>
 *     It caches the reference to the element but produces a different stream each time it's called
 * Created by kfgodel on 22/10/14.
 */
public class StreamOneElementSupplier<T> implements Supplier<Stream<T>> {

    private T element;

    @Override
    public Stream<T> get() {
        return Stream.of(element);
    }

    public static<T> StreamOneElementSupplier<T> using(T element) {
        StreamOneElementSupplier<T> supplier = new StreamOneElementSupplier<>();
        supplier.element = element;
        return supplier;
    }

}
