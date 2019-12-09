package ar.com.kfgodel.diamond.impl.types.parts.bounds;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.UpperOnlyTypeBounds;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the bounds supplier of a type variable (only upper)
 * Created by kfgodel on 28/09/14.
 */
public class TypeVariableBoundSupplier {

  public static Supplier<TypeBounds> create(Object nativeType) {
    return CachedValue.from(() -> {
      AnnotatedType[] upperBounds = getUpperBoundsFrom(nativeType);
      return UpperOnlyTypeBounds.create(typeListFrom(upperBounds));
    });
  }

  public static List<TypeInstance> typeListFrom(Object[] types) {
    return Arrays.stream(types)
      .map((type) -> Diamond.types().from(type))
      .collect(Collectors.toList());
  }

  private static AnnotatedType[] getUpperBoundsFrom(Object nativeType) {
    AnnotatedType[] upperBounds;
    if (nativeType instanceof AnnotatedTypeVariable) {
      upperBounds = ((AnnotatedTypeVariable) nativeType).getAnnotatedBounds();
    } else if (nativeType instanceof TypeVariable) {
      upperBounds = ((TypeVariable) nativeType).getAnnotatedBounds();
    } else {
      throw new DiamondException("The type[" + nativeType + "] is not a type variable representation");
    }
    return upperBounds;
  }

}
