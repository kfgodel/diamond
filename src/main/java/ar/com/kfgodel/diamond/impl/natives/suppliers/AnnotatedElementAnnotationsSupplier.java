package ar.com.kfgodel.diamond.impl.natives.suppliers;

import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * This type represents a supplier of annotations for an annotated type
 * Created by kfgodel on 28/09/14.
 */
public class AnnotatedElementAnnotationsSupplier implements Supplier<Nary<Annotation>> {

  private AnnotatedElement annotatedType;

  @Override
  public Nary<Annotation> get() {
    return Nary.from(Arrays.stream(annotatedType.getAnnotations()));
  }

  public static AnnotatedElementAnnotationsSupplier create(AnnotatedElement annotatedType) {
    AnnotatedElementAnnotationsSupplier supplier = new AnnotatedElementAnnotationsSupplier();
    supplier.annotatedType = annotatedType;
    return supplier;
  }

}
