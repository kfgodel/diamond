package ar.com.kfgodel.diamond.impl.types.parts.names;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.impl.types.description.names.UnnamedTypeDescription;
import ar.com.kfgodel.diamond.impl.types.names.TypeInstanceNames;

import java.util.function.Supplier;

/**
 * This type represents the supplier of names for a type that has no name
 * Created by kfgodel on 29/09/14.
 */
public class NoNamesSupplier implements Supplier<TypeNames> {

  private TypeInstance ownerType;

  @Override
  public TypeNames get() {
    return TypeInstanceNames.create(ownerType, UnnamedTypeDescription.create("<unknown>"));
  }

  public static NoNamesSupplier create(TypeInstance unnamedType) {
    NoNamesSupplier supplier = new NoNamesSupplier();
    supplier.ownerType = unnamedType;
    return supplier;
  }

}
