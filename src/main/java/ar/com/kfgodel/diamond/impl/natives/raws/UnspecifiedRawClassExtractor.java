package ar.com.kfgodel.diamond.impl.natives.raws;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * This type knows how to extract the raw class from its generified version.<br>
 * This fragment is specially needed for generic arrays, and is taken from: http://www.java2s.com/Code/Java/Reflection/GetRawClass.htm
 * Created by kfgodel on 21/09/14.
 */
public class UnspecifiedRawClassExtractor {

  /**
   * Tries to get the type class that represents all the types present in the set.<br>
   * Choosing object, if no other match
   *
   * @param rawComponentClasses The set of classes
   * @return The chosen type representative of the set classes
   */
  public static Class<?> coalesce(Set<Class<?>> rawComponentClasses) {
    if (rawComponentClasses.size() == 1) {
      return rawComponentClasses.stream().findFirst().get();
    }
    // The common super type for all other types
    return Object.class;
  }

  /**
   * Generic type extractor method for unspecific Type instances.<br>
   * For bound types this will return the upper bounds list, or Object if no bound.<br>
   * This method may be called recursively from generified types.<br>
   *
   * @param type The instance to degenerify
   * @return The list of raw classes
   */
  public static Set<Class<?>> fromUnspecific(final Type type) {
    return GenericTypeRawClassExtractor.create().apply(type);
  }

}
