package ar.com.kfgodel.diamond.impl.types.categories;

import java.util.function.Predicate;

/**
 * This type represents the category for types that represent annotations.<br>
 *   Equivalent to {@link Class#isAnnotation()}
 * Created by kfgodel on 03/02/15.
 */
public class AnnotationCategory extends ClassBasedCategorySupport {
  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isAnnotation;
  }

  public static AnnotationCategory create() {
    AnnotationCategory category = new AnnotationCategory();
    return category;
  }

  @Override
  public String name() {
    return "ANNOTATION";
  }

}
