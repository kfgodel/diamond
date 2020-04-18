package ar.com.kfgodel.diamond.impl.types.categories;

import java.util.function.Predicate;

/**
 * This type represents the category for classes that are anonymous classes
 *   Equivalent to {@link Class#isAnonymousClass()}
 * Created by kfgodel on 03/02/15.
 */
public class AnonymousCategory extends ClassBasedCategorySupport {
  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isAnonymousClass;
  }

  public static AnonymousCategory create() {
    AnonymousCategory category = new AnonymousCategory();
    return category;
  }

  @Override
  public String name() {
    return "ANONYMOUS";
  }

}
