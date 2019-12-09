package ar.com.kfgodel.diamond.impl.types.runtime;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.runtime.RuntimeTypeHierarchy;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This class is the default implementation for a type runtime hierarchy
 * Date: 8/12/19 - 21:01
 */
public class DefaultRuntimeHierarchy implements RuntimeTypeHierarchy {

  private Supplier<Nary<TypeInstance>> superclass;

  @Override
  public Nary<TypeInstance> superclass() {
    return superclass.get();
  }

  public static DefaultRuntimeHierarchy create(InheritanceDescription description) {
    DefaultRuntimeHierarchy hierarchy = new DefaultRuntimeHierarchy();
    hierarchy.superclass = description.getSuperclassSupplier();
    return hierarchy;
  }

}
