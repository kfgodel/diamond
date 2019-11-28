package ar.com.kfgodel.diamond.impl.natives.raws;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;

/**
 * This type extract the raw classes used to represent the behavior of a generic type in runtime.<br>
 * <br>
 *   Implementation note: Due to {@link Type}'s lack of protocol a cast needs to be done for each possible subtype and derived
 * to an specific sub-case
 * Date: 27/11/19 - 21:05
 */
public class GenericTypeRawClassExtractor implements RawClassExtractor<Type> {

  @Override
  public Set<Class<?>> apply(Type type) {
    if (Class.class.isInstance(type)) {
      return NativeClassRawClassExtractor.create().apply((Class<?>) type);
    }
    if (ParameterizedType.class.isInstance(type)) {
      return ParameterizedRawClassExtractor.create().apply((ParameterizedType) type);
    }
    if (GenericArrayType.class.isInstance(type)) {
      return GenericArrayRawClassExtractor.create().apply((GenericArrayType) type);
    }
    if (WildcardType.class.isInstance(type)) {
      return WildcardTypeRawClassExtractor.create().apply((WildcardType) type);
    }
    if (TypeVariable.class.isInstance(type)) {
      return TypeVariableRawClassExtractor.create().apply((TypeVariable<?>) type);
    }
    throw new DiamondException("The given generic type[" + type + "] is not one of the known sub-types. " +
      "Is it a new concept in the language?");
  }

  public static GenericTypeRawClassExtractor create() {
    GenericTypeRawClassExtractor extractor = new GenericTypeRawClassExtractor();
    return extractor;
  }

}
