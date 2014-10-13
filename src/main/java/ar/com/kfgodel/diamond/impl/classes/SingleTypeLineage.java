package ar.com.kfgodel.diamond.impl.classes;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

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
    public Stream<TypeInstance> allMembers() {
        return Arrays.asList(onlyType).stream();
    }

    @Override
    public Optional<TypeInstance> ancestorOf(TypeInstance descendant) {
        return Optional.empty();
    }

    @Override
    public Optional<TypeInstance> descendantOf(TypeInstance ancestor) {
        return Optional.empty();
    }

    public static SingleTypeLineage create(TypeInstance singleType) {
        SingleTypeLineage lineage = new SingleTypeLineage();
        lineage.onlyType = singleType;
        return lineage;
    }

}
