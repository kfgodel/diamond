package ar.com.kfgodel.diamond.impl.types.runtime;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.api.types.runtime.RuntimeTypeHierarchy;
import ar.com.kfgodel.diamond.impl.types.lineage.DefaultTypeLineage;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This class is the default implementation for a type runtime hierarchy
 * Date: 8/12/19 - 21:01
 */
public class DefaultRuntimeHierarchy implements RuntimeTypeHierarchy {

  private Supplier<Nary<TypeInstance>> superclass;
  private Supplier<Nary<TypeInstance>> interfaces;
  private TypeInstance type;

  @Override
  public Nary<TypeInstance> superclass() {
    return superclass.get();
  }

  @Override
  public Nary<TypeInstance> interfaces() {
    return interfaces.get();
  }

  @Override
  public TypeLineage lineage() {
    return DefaultTypeLineage.create(
      type.runtime().type(),
      (type) -> type.runtime().hierarchy().superclass(),
      (type) -> type.runtime().hierarchy().supertypes()
    );
  }

  @Override
  public Nary<TypeInstance> supertypes() {
    return superclass().concat(interfaces());
  }

  public static DefaultRuntimeHierarchy create(TypeInstance type, InheritanceDescription description) {
    DefaultRuntimeHierarchy hierarchy = new DefaultRuntimeHierarchy();
    hierarchy.type = type;
    hierarchy.superclass = description.getSuperclassSupplier();
    hierarchy.interfaces = description.getInterfacesSupplier();
    return hierarchy;
  }

}
