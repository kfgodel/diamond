package ar.com.kfgodel.diamond.impl.types.packages;

import ar.com.kfgodel.diamond.api.types.packages.TypePackage;

/**
 * This type represents the definition of equality for packages
 * Created by kfgodel on 09/01/16.
 */
public class PackageEquality {

  public static final PackageEquality INSTANCE = new PackageEquality();

  /**
   * Compares the given package with the object indicating if they are equals.<br>
   *     They are equals if have same name
   * @param one The package to compare
   * @param obj The object to be compared with
   * @return true if represent the same method
   */
  public boolean areEquals(TypePackage one, Object obj){
    if(one == obj){
      return true;
    }
    if(one == null || obj == null || !(obj instanceof TypePackage)){
      return false;
    }
    TypePackage other = (TypePackage) obj;
    return one.name().equals(other.name());
  }

  /**
   * Generates the hashcode compatible with this equality definition
   * @param typePackage The package to hash
   * @return The code that can be compared
   */
  public int hashcodeFor(TypePackage typePackage){
    return typePackage.name().hashCode();
  }

}
