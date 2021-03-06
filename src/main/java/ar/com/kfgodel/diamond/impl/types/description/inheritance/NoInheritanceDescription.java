package ar.com.kfgodel.diamond.impl.types.description.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents the description for a type that has no inheritance
 * Created by kfgodel on 04/11/14.
 */
public class NoInheritanceDescription implements InheritanceDescription {

  public static final NoInheritanceDescription INSTANCE = new NoInheritanceDescription();

  @Override
  public Supplier<Unary<TypeInstance>> getSuperclassSupplier() {
    return Nary::empty;
  }

  @Override
  public Supplier<Unary<TypeInstance>> getExtendedTypeSupplier() {
    return Nary::empty;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getInterfacesSupplier() {
    return Nary::empty;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getImplementedTypesSupplier() {
    return Nary::empty;
  }
}
