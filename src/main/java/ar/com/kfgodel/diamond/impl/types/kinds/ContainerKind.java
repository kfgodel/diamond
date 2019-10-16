package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.Kinds;

import java.util.Collection;
import java.util.Map;

/**
 * This type represents the kind of container types (have other objects inside)
 * Created by kfgodel on 03/02/15.
 */
public class ContainerKind implements Kind {
  @Override
  public boolean contains(TypeInstance testedType) {
    TypeInheritance typeInheritance = testedType.inheritance();
    return typeInheritance.isSubTypeOfNative(Collection.class) || typeInheritance.isSubTypeOfNative(Map.class) || Kinds.ARRAY.contains(testedType);
  }

  public static ContainerKind create() {
    ContainerKind kind = new ContainerKind();
    return kind;
  }

}
