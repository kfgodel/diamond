package ar.com.kfgodel.streams;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This type knows how to check for equality between two streams by checking equality of each element.<br>
 * This method cannot be used for infinite equal streams (as it won't finish)
 * Created by kfgodel on 16/10/14.
 */
public class StreamEquality {

  public static final StreamEquality INSTANCE = new StreamEquality();

  /**
   * Indicates if the contents of both streams can be considered equal, comparing each element and consuming both streams
   *
   * @param one   One stream to compare
   * @param other The other
   * @return true if the have the equal elements in the same order, and the same count
   */
  public boolean areEquals(Stream<?> one, Stream<?> other) {
    Iterator<?> oneIterator = one.iterator();
    Iterator<?> otherIterator = other.iterator();
    while (oneIterator.hasNext() && otherIterator.hasNext()) {
      Object oneElement = oneIterator.next();
      Object otherElement = otherIterator.next();
      if (!Objects.equals(oneElement, otherElement)) {
        return false;
      }
    }
    boolean bothDepleted = oneIterator.hasNext() == otherIterator.hasNext();
    return bothDepleted;
  }
}
