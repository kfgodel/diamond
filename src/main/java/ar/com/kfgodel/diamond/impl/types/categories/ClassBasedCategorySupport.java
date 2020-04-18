package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

import java.util.function.Predicate;

/**
 * This type serves as base class for categories that are expressible as a predicate over native class instances
 *
 * Created by kfgodel on 03/02/15.
 */
public abstract class ClassBasedCategorySupport implements TypeCategory {

  @Override
  public boolean contains(TypeInstance testedType) {
    Predicate<Class<?>> classPredicate = getClassPredicate();
    return testedType.runtime().classes()
      .anyMatch(classPredicate);
  }

  /**
   * The predicate to evaluate over native class instances to decide if this category contains them
   *
   * @return The predicate to use to decide inclusion
   */
  protected abstract Predicate<Class<?>> getClassPredicate();

  @Override
  public String toString() {
    return name();
  }

}
