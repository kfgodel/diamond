package ar.com.kfgodel.diamond.impl.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.lineage.FunctionBasedTypeLineage;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the inheritance information of a type, based on supliers
 * Created by kfgodel on 05/10/14.
 */
public class SuppliedTypesInheritance implements TypeInheritance {

    private TypeInstance type;
    private Supplier<Nary<TypeInstance>> superclass;
    private Supplier<Nary<TypeInstance>> extendedType;
    private Supplier<Nary<TypeInstance>> interfaces;

    @Override
    public Nary<TypeInstance> superclass() {
        return superclass.get();
    }

    @Override
    public Nary<TypeInstance> interfaces() {
        return interfaces.get();
    }

    @Override
    public Nary<TypeInstance> extendedType() {
        return this.extendedType.get();
    }

    @Override
    public TypeLineage typeLineage() {
        return FunctionBasedTypeLineage.create(type, (type) -> type.inheritance().extendedType());
    }

    @Override
    public TypeLineage classLineage() {
        return FunctionBasedTypeLineage.create(type, (type) -> type.inheritance().superclass());
    }

    @Override
    public Nary<TypeInstance> supertypes() {
        return superclass().joinedWith(interfaces());
    }

    public static SuppliedTypesInheritance create(TypeInstance type, InheritanceDescription description) {
        SuppliedTypesInheritance inheritance = new SuppliedTypesInheritance();
        inheritance.type = type;
        inheritance.superclass = description.getSuperclassSupplier();
        inheritance.extendedType = description.getExtendedTypeSupplier();
        inheritance.interfaces = description.getInterfacesSupplier();
        return inheritance;
    }


}
