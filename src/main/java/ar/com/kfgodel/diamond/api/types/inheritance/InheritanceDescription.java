package ar.com.kfgodel.diamond.api.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents the inheritance description of a type
 * Created by kfgodel on 04/11/14.
 */
public interface InheritanceDescription {

  /**
   * @return The supplier of superclass for this type
   */
  Supplier<Unary<TypeInstance>> getSuperclassSupplier();

  /**
   * @return The supplier of extended types
   */
  Supplier<Unary<TypeInstance>> getExtendedTypeSupplier();

  /**
   * @return The supplier of the type implemented interfaces
   */
  Supplier<Nary<TypeInstance>> getInterfacesSupplier();

  /**
   * @return The supplier of compile time interface types
   */
  Supplier<Nary<TypeInstance>> getImplementedTypesSupplier();
}
