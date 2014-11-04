package ar.com.kfgodel.diamond.impl.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.lineage.SingleTypeLineage;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the inheritance of type that has no parent types
 * Created by kfgodel on 05/10/14.
 */
public class NoParentsInheritance implements TypeInheritance {

    private TypeInstance type;

    @Override
    public Optional<TypeInstance> extendedType() {
        return Optional.empty();
    }

    @Override
    public Optional<TypeInstance> superclass() {
        return Optional.empty();
    }

    @Override
    public Stream<TypeInstance> interfaces() {
        return Stream.empty();
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
