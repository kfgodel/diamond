package ar.com.kfgodel.diamond.impl.types.inheritance;

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
  private Supplier<Nary<TypeInstance>> extendedType;
  private Supplier<Nary<TypeInstance>> implementedTypes;

  @Override
  public Nary<TypeInstance> implementedTypes() {
    return implementedTypes.get();
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
    return extendedType().concat(implementedTypes());
  }

  public static SuppliedTypesInheritance create(TypeInstance type, InheritanceDescription description) {
    SuppliedTypesInheritance inheritance = new SuppliedTypesInheritance();
    inheritance.type = type;
    inheritance.extendedType = description.getExtendedTypeSupplier();
    inheritance.implementedTypes = description.getImplementedTypesSupplier();
    return inheritance;
  }


}
