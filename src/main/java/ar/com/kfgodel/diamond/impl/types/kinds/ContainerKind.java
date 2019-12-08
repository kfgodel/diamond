package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
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
    return testedType.is().subTypeOf(Diamond.of(Collection.class))
      || testedType.is().subTypeOf(Diamond.of(Map.class))
      || Kinds.ARRAY.contains(testedType);
  }

  public static ContainerKind create() {
    ContainerKind kind = new ContainerKind();
    return kind;
  }

}
