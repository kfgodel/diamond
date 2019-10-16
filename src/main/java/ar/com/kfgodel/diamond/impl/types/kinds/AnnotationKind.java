package ar.com.kfgodel.diamond.impl.types.kinds;

import java.util.function.Predicate;

/**
 * This type represents the kind of annotation types
 * Created by kfgodel on 03/02/15.
 */
public class AnnotationKind extends NativeClassKindSupport {
  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isAnnotation;
  }

  public static AnnotationKind create() {
    AnnotationKind kind = new AnnotationKind();
    return kind;
  }

}
