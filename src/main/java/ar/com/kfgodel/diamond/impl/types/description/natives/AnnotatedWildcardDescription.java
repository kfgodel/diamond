package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleBounds;
import ar.com.kfgodel.diamond.impl.types.description.support.AnnotatedTypeDescriptionSupport;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.WildcardType;
import java.util.function.Supplier;

/**
 * This type represents an annotated native wildcard type description
 * Created by kfgodel on 28/09/14.
 */
public class AnnotatedWildcardDescription extends AnnotatedTypeDescriptionSupport {

  private AnnotatedWildcardType nativeType;

  @Override
  protected TypeDescription createUnannotatedDescription() {
    return WildcardTypeDescription.create((WildcardType) nativeType.getType());
  }

  @Override
  protected AnnotatedType getAnnotatedType() {
    return nativeType;
  }

  @Override
  public Supplier<TypeBounds> getBounds() {
    return CachedValue.from(() -> {
      final Nary<TypeInstance> upperBounds = Diamond.types().from(nativeType.getAnnotatedUpperBounds());
      final Nary<TypeInstance> lowerBounds = Diamond.types().from(nativeType.getAnnotatedLowerBounds());
      return DoubleBounds.create(upperBounds, lowerBounds);
    });
  }

  public static AnnotatedWildcardDescription create(AnnotatedWildcardType nativeType) {
    AnnotatedWildcardDescription description = new AnnotatedWildcardDescription();
    description.nativeType = nativeType;
    return description;
  }

}
