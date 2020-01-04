package ar.com.kfgodel.diamond.impl.types.parts.packages;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

/**
 * This type represents the supplier of packages for types that have a native class
 * Created by kfgodel on 07/11/14.
 */
public class TypePackageSupplier  {

  /**
   * Extracts the package from the given class if it has one.<br>
   *  (Some classes have none)
   * @param nativeClass The class to take the package from
   * @return Empty or the package of the class
   */
  public static Unary<TypePackage> from(Class<?> nativeClass){
    return Nary.of(nativeClass.getPackage())
      .map(nativePackage -> Diamond.packages().from(nativePackage))
      .unique();
  }
}
