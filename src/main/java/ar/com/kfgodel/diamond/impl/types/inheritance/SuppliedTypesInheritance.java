package ar.com.kfgodel.diamond.impl.types.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.lineage.FunctionBasedTypeLineage;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the inheritance information of a type, based on suppliers
 * Created by kfgodel on 05/10/14.
 */
public class SuppliedTypesInheritance implements TypeInheritance {

    private TypeInstance type;
    private Supplier<Nary<TypeInstance>> superclass;
    private Supplier<Nary<TypeInstance>> extendedType;
    private Supplier<Nary<TypeInstance>> interfaces;
    private Supplier<Nary<TypeInstance>> implementedTypes;

    @Override
    public Nary<TypeInstance> superclass() {
        return superclass.get();
    }

    @Override
    public Nary<TypeInstance> implementedTypes() {
        return implementedTypes.get();
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
    public Nary<TypeInstance> supertypes() {
        return extendedType().joinedWith(implementedTypes());
    }

    @Override
    public boolean isSubTypeOf(TypeInstance objectType) {
        return type.generics().runtimeType().isAssignableTo(objectType.generics().runtimeType());
    }

    @Override
    public boolean isSubTypeOfNative(Class<?> nativeType) {
        return isSubTypeOf(Diamond.of(nativeType));
    }

    @Override
    public boolean isSuperTypeOf(TypeInstance objectType) {
        return objectType.inheritance().isSubTypeOf(type);
    }

    @Override
    public boolean isSuperTypeOfNative(Class<?> nativeType) {
        return isSuperTypeOf(Diamond.of(nativeType));
    }

    public static SuppliedTypesInheritance create(TypeInstance type, InheritanceDescription description) {
        SuppliedTypesInheritance inheritance = new SuppliedTypesInheritance();
        inheritance.type = type;
        inheritance.superclass = description.getSuperclassSupplier();
        inheritance.extendedType = description.getExtendedTypeSupplier();
        inheritance.interfaces = description.getInterfacesSupplier();
        inheritance.implementedTypes = description.getImplementedTypesSupplier();
        return inheritance;
    }


}
