package ar.com.kfgodel.diamond.impl.types.categories;

import java.util.function.Predicate;

/**
 * This type represents the category of types that represent interfaces
 *   Equivalent to {@link Class#isInterface()}
 * Created by kfgodel on 03/02/15.
 */
public class InterfaceCategory extends ClassBasedCategorySupport {
  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isInterface;
  }

  public static InterfaceCategory create() {
    InterfaceCategory category = new InterfaceCategory();
    return category;
  }

  @Override
  public String name() {
    return "INTERFACE";
  }

}
