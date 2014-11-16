package ar.com.kfgodel.streams;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the fragment that allows getting a stream from a collection
 * Created by kfgodel on 17/11/14.
 */
public class StreamFromCollection<T> implements Supplier<Stream<T>> {

    private Collection<T> aCollection;

    @Override
    public Stream<T> get() {
        return aCollection.stream();
    }

    public static<T> StreamFromCollection<T> create(Collection<T> aCollection) {
        StreamFromCollection streamer = new StreamFromCollection();
        streamer.aCollection = aCollection;
        return streamer;
    }

}
