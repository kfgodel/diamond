package ar.com.kfgodel.diamond.impl.types.categories;

import java.util.function.Predicate;

/**
 * This type represents the category of types that are considered primitive (vs. reference types)
 *   Equivalent to {@link Class#isPrimitive()}
 * Created by kfgodel on 03/02/15.
 */
public class PrimitiveCategory extends ClassBasedCategorySupport {

  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isPrimitive;
  }

  public static PrimitiveCategory create() {
    PrimitiveCategory category = new PrimitiveCategory();
    return category;
  }

}
