package ar.com.kfgodel.diamond.impl.natives.raws;

import ar.com.kfgodel.colls.Sets;

import java.util.Set;

/**
 * Implementation for native classes that returns whatever receives as parameter
 * Date: 27/11/19 - 20:30
 */
public class NativeClassRawClassExtractor implements RawClassExtractor<Class<?>> {

  @Override
  public Set<Class<?>> apply(Class<?> nativeClass) {
    return Sets.newLinkedSet(nativeClass);
  }

  public static NativeClassRawClassExtractor create() {
    NativeClassRawClassExtractor extractor = new NativeClassRawClassExtractor();
    return extractor;
  }

}
