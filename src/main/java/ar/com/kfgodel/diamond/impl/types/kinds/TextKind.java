package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;

/**
 * This type represents the kind of textual types
 * Created by kfgodel on 03/02/15.
 */
public class TextKind implements Kind {
  @Override
  public boolean contains(TypeInstance testedType) {
    return testedType.inheritance().isSubTypeOfNative(CharSequence.class);
  }

  public static TextKind create() {
    TextKind kind = new TextKind();
    return kind;
  }

}
