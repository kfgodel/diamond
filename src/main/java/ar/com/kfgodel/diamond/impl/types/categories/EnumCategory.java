package ar.com.kfgodel.diamond.impl.types.categories;

import java.util.function.Predicate;

/**
 * This type represents the category of types that are instances of {@link Enum}
 *   Equivalent to {@link Class#isEnum()}
 * Created by kfgodel on 03/02/15.
 */
public class EnumCategory extends ClassBasedCategorySupport {

  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isEnum;
  }

  public static EnumCategory create() {
    EnumCategory category = new EnumCategory();
    return category;
  }

  @Override
  public String name() {
    return "ENUM";
  }
}
