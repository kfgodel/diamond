package ar.com.kfgodel.diamond.impl.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.lineage.SingleTypeLineage;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

/**
 * This type represents the inheritance of type that has no parent types
 * Created by kfgodel on 05/10/14.
 */
public class NoParentsInheritance implements TypeInheritance {

    private TypeInstance type;

    @Override
    public Nary<TypeInstance> extendedType() {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeInstance> superclass() {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeInstance> interfaces() {
        return NaryFromNative.empty();
    }

    @Override
    public TypeLineage typeLineage() {
        return SingleTypeLineage.create(type);
    }

    @Override
    public TypeLineage classLineage() {
        return SingleTypeLineage.create(type);
    }

    public static NoParentsInheritance create(TypeInstance type) {
        NoParentsInheritance inheritance = new NoParentsInheritance();
        inheritance.type = type;
        return inheritance;
    }

}
