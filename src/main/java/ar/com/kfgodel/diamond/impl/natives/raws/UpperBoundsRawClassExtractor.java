package ar.com.kfgodel.diamond.impl.natives.raws;

import ar.com.kfgodel.colls.Sets;

import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This type know how to get the runtime classes that comprise the types used in runtime as upper bounds
 * for aan array of generic types
 *
 * Date: 27/11/19 - 20:55
 */
public class UpperBoundsRawClassExtractor implements RawClassExtractor<Type[]> {

  @Override
  public Set<Class<?>> apply(Type[] upperBounds) {
    if (upperBounds== null || upperBounds.length == 0) {
      // Unbounded means that we only know that's an Object
      return Sets.newLinkedSet(Object.class);
    }
    Set<Class<?>> rawUpperBounds = new LinkedHashSet<>();
    for (Type upperBound : upperBounds) {
      Set<Class<?>> expandedUpperBound = UnspecifiedRawClassExtractor.fromUnspecific(upperBound);
      rawUpperBounds.addAll(expandedUpperBound);
    }
    return rawUpperBounds;
  }

  public static UpperBoundsRawClassExtractor create() {
    UpperBoundsRawClassExtractor extractor = new UpperBoundsRawClassExtractor();
    return extractor;
  }

}
