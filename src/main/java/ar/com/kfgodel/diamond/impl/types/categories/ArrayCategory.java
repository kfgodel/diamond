package ar.com.kfgodel.diamond.impl.types.categories;

import java.util.function.Predicate;

/**
 * This type represents the category for array types
 *   Equivalent to {@link Class#isArray()}
 * Created by kfgodel on 03/02/15.
 */
public class ArrayCategory extends ClassBasedCategorySupport {
  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isArray;
  }

  public static ArrayCategory create() {
    ArrayCategory category = new ArrayCategory();
    return category;
  }

}
