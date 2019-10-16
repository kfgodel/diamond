package ar.com.kfgodel.diamond.impl.types.packages;

import ar.com.kfgodel.diamond.api.types.packages.PackageDescription;

/**
 * This type represents the descriptor of native packages
 * Created by kfgodel on 05/11/14.
 */
public class PackageDescriptor {

  public static final PackageDescriptor INSTANCE = PackageDescriptor.create();

  /**
   * Creates a description of the given native package based on its features
   *
   * @param nativePackage The package to describe
   * @return The description for diamond
   */
  public PackageDescription describe(Package nativePackage) {
    return NativePackageDescription.create(nativePackage);
  }

  public static PackageDescriptor create() {
    PackageDescriptor descriptor = new PackageDescriptor();
    return descriptor;
  }

}
