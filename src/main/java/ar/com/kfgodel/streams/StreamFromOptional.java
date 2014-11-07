package ar.com.kfgodel.streams;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the creator of stream from an Optional
 * Created by kfgodel on 06/11/14.
 */
public class StreamFromOptional {

    public static<T> Stream<T> create(Optional<T> optional) {
        if(optional.isPresent()){
            return Stream.of(optional.get());
        }
        return Stream.empty();
    }

}
