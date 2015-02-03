package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.KindOf;

/**
 * This type represents the kind of numeric types
 * Created by kfgodel on 03/02/15.
 */
public class NumericKind implements Kind {
    @Override
    public boolean contains(TypeInstance testedType) {
        // Either is a boxed number, or is a primitive (excluding boolean)
        return testedType.inheritance().isSubTypeOfNative(Number.class) || (KindOf.PRIMITIVE.contains(testedType) && !KindOf.BOOLEAN.contains(testedType));
    }

    public static NumericKind create() {
        NumericKind kind = new NumericKind();
        return kind;
    }

}
