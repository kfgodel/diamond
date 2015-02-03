package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.Kinds;

/**
 * This type represents the kind of classes (abstract or concrete, that are not interfaces)
 * Created by kfgodel on 03/02/15.
 */
public class ClassKind implements Kind {
    @Override
    public boolean contains(TypeInstance testedType) {
        //I think there's no way to define this in a positive manner
        return Kinds.REFERENCE.contains(testedType) && !Kinds.INTERFACE.contains(testedType);
    }

    public static ClassKind create() {
        ClassKind kind = new ClassKind();
        return kind;
    }

}
