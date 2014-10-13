package ar.com.kfgodel.diamond.impl.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.classes.FunctionBasedTypeLineage;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents the inheritance information of a type, based on supliers
 * Created by kfgodel on 05/10/14.
 */
public class SuppliedTypesInheritance implements TypeInheritance {

    private TypeInstance type;
    private LazyValue<Optional<TypeInstance>> superclass;
    private LazyValue<Optional<TypeInstance>> extendedType;

    @Override
    public Optional<TypeInstance> superclass() {
        return superclass.get();
    }

    @Override
    public Optional<TypeInstance> extendedType() {
        return this.extendedType.get();
    }

    @Override
    public TypeLineage typeLineage() {
        return FunctionBasedTypeLineage.createType(type);
    }

    @Override
    public TypeLineage classLineage() {
        return FunctionBasedTypeLineage.createClass(type);
    }

    public static SuppliedTypesInheritance create(TypeInstance type, Supplier<Optional<TypeInstance>> superclassSupplier, Supplier<Optional<TypeInstance>> extendedTypeSupplier) {
        SuppliedTypesInheritance inheritance = new SuppliedTypesInheritance();
        inheritance.type = type;
        inheritance.superclass = SuppliedValue.create(superclassSupplier);
        inheritance.extendedType = SuppliedValue.create(extendedTypeSupplier);
        return inheritance;
    }


}
