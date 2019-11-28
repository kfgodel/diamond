package ar.com.kfgodel.diamond.impl.natives.raws;

import ar.com.kfgodel.colls.Sets;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * This type knows how to extract the raw runtime type used in runtime for a generic array type
 * Date: 27/11/19 - 20:43
 */
public class GenericArrayRawClassExtractor implements RawClassExtractor<GenericArrayType> {
  @Override
  public Set<Class<?>> apply(GenericArrayType genericArrayType) {
    Class<?> rawComponentClass = calculateRawComponentClass(genericArrayType);
    // We need to create an array to get the actual runtime class
    final Object sampleArray = Array.newInstance(rawComponentClass, 0);
    final Class<?> rawClass = sampleArray.getClass();
    return Sets.newLinkedSet(rawClass);
  }

  private Class<?> calculateRawComponentClass(GenericArrayType genericArrayType) {
    Type componentType = genericArrayType.getGenericComponentType();
    Set<Class<?>> rawComponentClasses = UnspecifiedRawClassExtractor.fromUnspecific(componentType);
    return UnspecifiedRawClassExtractor.coalesce(rawComponentClasses);
  }

  public static GenericArrayRawClassExtractor create() {
    GenericArrayRawClassExtractor extractor = new GenericArrayRawClassExtractor();
    return extractor;
  }

}
