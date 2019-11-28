package ar.com.kfgodel.diamond.impl.natives.raws;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * This type extracts the raw class used for a parameterized type on runtime
 * Date: 27/11/19 - 20:40
 */
public class ParameterizedRawClassExtractor implements RawClassExtractor<ParameterizedType> {

  @Override
  public Set<Class<?>> apply(ParameterizedType parameterizedType) {
    Type unparameterizedType = parameterizedType.getRawType();
    return UnspecifiedRawClassExtractor.fromUnspecific(unparameterizedType);
  }

  public static ParameterizedRawClassExtractor create() {
    ParameterizedRawClassExtractor extractor = new ParameterizedRawClassExtractor();
    return extractor;
  }

}
