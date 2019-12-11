package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.SingleBound;
import ar.com.kfgodel.diamond.impl.types.description.support.AnnotatedTypeDescriptionSupport;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.TypeVariable;
import java.util.function.Supplier;

/**
 * This type represents a description for an annotated native type variable
 * Created by kfgodel on 28/09/14.
 */
public class AnnotatedTypeVariableDescription extends AnnotatedTypeDescriptionSupport {

  private AnnotatedTypeVariable nativeType;

  @Override
  protected TypeDescription createUnannotatedDescription() {
    return TypeVariableDescription.create((TypeVariable) nativeType.getType());
  }

  @Override
  protected AnnotatedType getAnnotatedType() {
    return nativeType;
  }

  @Override
  public Supplier<TypeBounds> getBounds() {
    return CachedValue.from(() -> {
      final Nary<TypeInstance> upperBounds = Diamond.types().from(nativeType.getAnnotatedBounds());
      return SingleBound.create(upperBounds);
    });
  }

  public static AnnotatedTypeVariableDescription create(AnnotatedTypeVariable nativeType) {
    AnnotatedTypeVariableDescription description = new AnnotatedTypeVariableDescription();
    description.nativeType = nativeType;
    return description;
  }

}
