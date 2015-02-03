package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.KindOf;

/**
 * This type represents the kind of value types
 * Created by kfgodel on 03/02/15.
 */
public class ValueKind implements Kind {
    @Override
    public boolean contains(TypeInstance testedType) {
        return KindOf.TEXT.contains(testedType) || KindOf.BOOLEAN.contains(testedType) || KindOf.NUMERIC.contains(testedType);
    }

    public static ValueKind create() {
        ValueKind kind = new ValueKind();
        return kind;
    }

}
