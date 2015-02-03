package ar.com.kfgodel.diamond.impl.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;

/**
 * This type represents the kind of boolean types
 * Created by kfgodel on 03/02/15.
 */
public class BooleanKind implements Kind {
    @Override
    public boolean contains(TypeInstance testedType) {
        TypeInheritance typeInheritance = testedType.inheritance();
        return typeInheritance.isSubTypeOfNative(boolean.class) || typeInheritance.isSubTypeOfNative(Boolean.class);
    }

    public static BooleanKind create() {
        BooleanKind kind = new BooleanKind();
        return kind;
    }

}
