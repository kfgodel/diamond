package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.Kinds;

/**
 * This type represents the kind of value types
 * Created by kfgodel on 03/02/15.
 */
public class ValueKind implements Kind {
    @Override
    public boolean contains(TypeInstance testedType) {
        return Kinds.TEXT.contains(testedType) || Kinds.BOOLEAN.contains(testedType) || Kinds.NUMERIC.contains(testedType);
    }

    public static ValueKind create() {
        ValueKind kind = new ValueKind();
        return kind;
    }

}
