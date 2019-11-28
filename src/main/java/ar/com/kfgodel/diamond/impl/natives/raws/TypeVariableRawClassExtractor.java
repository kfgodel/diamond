package ar.com.kfgodel.diamond.impl.natives.raws;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Set;

/**
 * This type knows how to extract the raw classes used to represent the type upper bounds in runtime
 *
 * Date: 27/11/19 - 21:02
 */
public class TypeVariableRawClassExtractor implements RawClassExtractor<TypeVariable<?>> {
  @Override
  public Set<Class<?>> apply(TypeVariable<?> typeVariable) {
    final Type[] upperBounds = typeVariable.getBounds();
    return UpperBoundsRawClassExtractor.create().apply(upperBounds);
  }

  public static TypeVariableRawClassExtractor create() {
    TypeVariableRawClassExtractor extractor = new TypeVariableRawClassExtractor();
    return extractor;
  }

}
