package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;

import java.util.function.Predicate;

/**
 * This type serves as base class for kinds that are expressible as a predicate over native class intances
 * Created by kfgodel on 03/02/15.
 */
public abstract class NativeClassKindSupport implements Kind {
  @Override
  public boolean contains(TypeInstance testedType) {
    Predicate<Class<?>> classPredicate = getClassPredicate();
    return testedType.runtime().classes()
      .anyMatch(classPredicate);
  }

  /**
   * The predicate to evaluate over native class instances to decide if this kind contains them
   *
   * @return The predicate to use to decide inclusion
   */
  protected abstract Predicate<Class<?>> getClassPredicate();
}
