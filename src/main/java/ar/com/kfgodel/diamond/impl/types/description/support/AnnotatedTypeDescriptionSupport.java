package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.impl.natives.suppliers.AnnotatedElementAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.function.Supplier;

/**
 * This type serves as base for annotated types description that are extension of other descriptions
 * Created by kfgodel on 28/09/14.
 */
public abstract class AnnotatedTypeDescriptionSupport extends DelegatedDescriptionSupport {

  private TypeDescription unannotatedDescription;

  protected abstract TypeDescription createUnannotatedDescription();

  protected abstract AnnotatedType getAnnotatedType();

  @Override
  public Supplier<Nary<Annotation>> getAnnotations() {
    return AnnotatedElementAnnotationsSupplier.create(getAnnotatedType());
  }

  @Override
  protected TypeDescription getDelegateDescription() {
    if (unannotatedDescription == null) {
      unannotatedDescription = createUnannotatedDescription();
    }
    return unannotatedDescription;
  }

  @Override
  public Supplier<Nary<Object>> getReflectionTypeSupplier() {
    return CachedValue.from(()-> Nary.of(getAnnotatedType()));
  }
}
