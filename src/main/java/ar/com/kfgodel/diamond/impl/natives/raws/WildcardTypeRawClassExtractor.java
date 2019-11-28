package ar.com.kfgodel.diamond.impl.natives.raws;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Set;

/**
 * This type extracts the raw classes used in runtime to represent the bounds of a wildcard if it has any
 * Date: 27/11/19 - 21:00
 */
public class WildcardTypeRawClassExtractor implements RawClassExtractor<WildcardType> {

  @Override
  public Set<Class<?>> apply(WildcardType wildcardType) {
    final Type[] upperBounds = wildcardType.getUpperBounds();
    return UpperBoundsRawClassExtractor.create().apply(upperBounds);
  }

  public static WildcardTypeRawClassExtractor create() {
    WildcardTypeRawClassExtractor extractor = new WildcardTypeRawClassExtractor();
    return extractor;
  }

}
