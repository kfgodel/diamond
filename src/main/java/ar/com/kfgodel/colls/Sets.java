package ar.com.kfgodel.colls;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Helper class to extends some methods not found on Guava
 * Date: 27/11/19 - 20:37
 */
public class Sets {


  /**
   * Creates a new {@link LinkedHashSet} with the given elements
   * @param elements The elements to initialize the set with
   * @param <T> Type of elements on the set
   * @return The newly created set
   */
  public static <T> Set<T> newLinkedSet(T... elements) {
    final LinkedHashSet<T> set = new LinkedHashSet<>();
    for (T element : elements) {
      set.add(element);
    }
    return set;
  }
}
