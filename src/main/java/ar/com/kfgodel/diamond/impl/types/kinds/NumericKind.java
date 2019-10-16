package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.Kinds;

/**
 * This type represents the kind of numeric types
 * Created by kfgodel on 03/02/15.
 */
public class NumericKind implements Kind {
  @Override
  public boolean contains(TypeInstance testedType) {
    // Either is a boxed number, or is a primitive (excluding boolean)
    return testedType.inheritance().isSubTypeOfNative(Number.class) || (Kinds.PRIMITIVE.contains(testedType) && !Kinds.BOOLEAN.contains(testedType));
  }

  public static NumericKind create() {
    NumericKind kind = new NumericKind();
    return kind;
  }

}
