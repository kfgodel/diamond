package ar.com.kfgodel.hashcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * This type represents a helper for hashcode calculations
 * Created by kfgodel on 02/11/14.
 */
public class Hashcodes {

  /**
   * Calcultes the hashcode for the given separate elements, using the array definition of hashcode.<br>
   * This definition varies with each element hashcode
   *
   * @param elements The elements to hash
   * @return The calculated hashcode
   */
  public static int joining(Object... elements) {
    return Arrays.hashCode(elements);
  }

  /**
   * Calculates the hashcode of a stream elements  using the same definition as arrays
   *
   * @param stream The stream to consume and calculate the hashcode for
   * @return The resulting hashcode for elements hash
   */
  public static int forElementsIn(Stream<?> stream) {
    if (stream == null) {
      return 0;
    }
    int hash = 1;
    Iterator<?> iterator = stream.iterator();
    while (iterator.hasNext()) {
      Object element = iterator.next();
      int elementHash = element == null ? 0 : element.hashCode();
      hash = 31 * hash + elementHash;
    }
    return hash;
  }

}
