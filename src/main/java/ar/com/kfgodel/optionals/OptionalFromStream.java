package ar.com.kfgodel.optionals;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the converter to get an optional value from a stream.<br>
 * Created by kfgodel on 23/10/14.
 */
public class OptionalFromStream {

    /**
     * Gets the optional value found in the given stream.<br>
     *     If the stream contains more than one element, an exception is risen
     * @param stream The stream to take elements from
     * @param <T> The expected type of element
     * @return The optional element, or empty if none was in the stream
     */
    public static<T> Optional<T> create(Stream<T> stream){
        List<T> foundElements = stream.limit(2).collect(Collectors.toList());
        if(foundElements.size() > 1){
            throw new DiamondException("There's more than one element in the stream to create an optional: " +foundElements);
        }
        if(foundElements.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(foundElements.get(0));
    }
}
