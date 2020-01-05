package ar.com.kfgodel.diamond.impl.types.parts.packages;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;

import java.util.function.Supplier;

/**
 * This type represents the supplier of packages for types that have a native class
 * Created by kfgodel on 07/11/14.
 */
public class TypePackageSupplier implements Supplier<TypePackage> {

  private Class<?> nativeClass;

  @Override
  public TypePackage get() {
    final Package nativePackage = nativeClass.getPackage();
    if(nativePackage == null){
      // Some classes don't have package information
      return null;
    }
    return Diamond.packages().from(nativePackage);
  }

  public static TypePackageSupplier create(Class<?> nativeClass) {
    TypePackageSupplier supplier = new TypePackageSupplier();
    supplier.nativeClass = nativeClass;
    return supplier;
  }


}
