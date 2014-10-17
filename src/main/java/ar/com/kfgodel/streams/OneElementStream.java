package ar.com.kfgodel.streams;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * This type represents a one element stream creator
 * Created by kfgodel on 16/10/14.
 */
public class OneElementStream {

    /**
     * Creates a new Stream to access just one element
     * @param element The element to encapsulate in one stream
     * @param <T> The type of expected stream elements
     * @return The created one element stream
     */
    public static<T> Stream<T> create(T element) {
        T[] oneElementArray = (T[]) new Object[]{element};
        Stream<T> createdStream = Arrays.stream(oneElementArray);
        return createdStream;
    }

}
