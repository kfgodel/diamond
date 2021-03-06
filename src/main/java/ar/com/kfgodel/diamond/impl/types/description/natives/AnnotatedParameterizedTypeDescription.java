package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.support.AnnotatedTypeDescriptionSupport;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.ParameterizedType;
import java.util.function.Supplier;

/**
 * This type represents the description of an annotated parameterized native type
 * Created by kfgodel on 29/09/14.
 */
public class AnnotatedParameterizedTypeDescription extends AnnotatedTypeDescriptionSupport {

  private AnnotatedParameterizedType nativeType;

  @Override
  protected TypeDescription createUnannotatedDescription() {
    return ParameterizedTypeDescription.create((ParameterizedType) nativeType.getType());
  }

  @Override
  protected AnnotatedType getAnnotatedType() {
    return nativeType;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return CachedValues.adapting(() -> {
      return Diamond.types().from(nativeType.getAnnotatedActualTypeArguments());
    });
  }

  public static AnnotatedParameterizedTypeDescription create(AnnotatedParameterizedType nativeType) {
    AnnotatedParameterizedTypeDescription description = new AnnotatedParameterizedTypeDescription();
    description.nativeType = nativeType;
    return description;
  }

}
