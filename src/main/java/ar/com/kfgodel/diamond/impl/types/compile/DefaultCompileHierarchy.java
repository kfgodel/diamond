package ar.com.kfgodel.diamond.impl.types.compile;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.compile.CompileTimeHierarchy;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.lineage.FunctionBasedTypeLineage;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the inheritance information of a type, based on suppliers
 * Created by kfgodel on 05/10/14.
 */
public class DefaultCompileHierarchy implements CompileTimeHierarchy {

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
  public TypeLineage lineage() {
    return FunctionBasedTypeLineage.create(
      type,
      (type) -> type.hierarchy().extendedType(),
      (type) -> type.hierarchy().supertypes()
    );
  }

  @Override
  public Nary<TypeInstance> supertypes() {
    return extendedType().concat(implementedTypes());
  }

  public static DefaultCompileHierarchy create(TypeInstance type, InheritanceDescription description) {
    DefaultCompileHierarchy inheritance = new DefaultCompileHierarchy();
    inheritance.type = type;
    inheritance.extendedType = description.getExtendedTypeSupplier();
    inheritance.implementedTypes = description.getImplementedTypesSupplier();
    return inheritance;
  }


}
