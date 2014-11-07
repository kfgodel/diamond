package ar.com.kfgodel.diamond.impl.types.lineage;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.Arrays;

/**
 * This type represents a type lineage for types that don't extend another type
 * Created by kfgodel on 30/09/14.
 */
public class SingleTypeLineage implements TypeLineage {

    private TypeInstance onlyType;

    @Override
    public TypeInstance lowestDescendant() {
        return onlyType;
    }

    @Override
    public TypeInstance highestAncestor() {
        return onlyType;
    }

    @Override
    public Nary<TypeInstance> allMembers() {
        return NaryFromNative.create(Arrays.asList(onlyType).stream());
    }

    @Override
    public Nary<TypeInstance> ancestorOf(TypeInstance descendant) {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeInstance> descendantOf(TypeInstance ancestor) {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeInstance> inheritedInterfaces() {
        return NaryFromNative.empty();
    }

    public static SingleTypeLineage create(TypeInstance singleType) {
        SingleTypeLineage lineage = new SingleTypeLineage();
        lineage.onlyType = singleType;
        return lineage;
    }

}
