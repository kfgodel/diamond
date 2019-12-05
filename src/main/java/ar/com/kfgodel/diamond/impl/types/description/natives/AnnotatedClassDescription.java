package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.AnnotatedTypeDescriptionSupport;

import java.lang.reflect.AnnotatedType;

/**
 * This type represents an annotated native class type
 * Created by kfgodel on 29/09/14.
 */
public class AnnotatedClassDescription extends AnnotatedTypeDescriptionSupport {

  private AnnotatedType nativeType;

  @Override
  protected TypeDescription createUnannotatedDescription() {
    return ClassDescription.create((Class<?>) nativeType.getType());
  }

  @Override
  protected AnnotatedType getAnnotatedType() {
    return nativeType;
  }

  public static AnnotatedClassDescription create(AnnotatedType nativeType) {
    AnnotatedClassDescription description = new AnnotatedClassDescription();
    description.nativeType = nativeType;
    return description;
  }

}
