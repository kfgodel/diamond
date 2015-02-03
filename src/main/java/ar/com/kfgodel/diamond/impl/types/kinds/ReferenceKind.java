package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.Kinds;

/**
 * This type represents the kind of referential types (non primitives)
 * Created by kfgodel on 03/02/15.
 */
public class ReferenceKind implements Kind {
    @Override
    public boolean contains(TypeInstance testedType) {
        //I think there's no current way to define this in a positive way
        return !Kinds.PRIMITIVE.contains(testedType);
    }

    public static ReferenceKind create() {
        ReferenceKind kind = new ReferenceKind();
        return kind;
    }

}
