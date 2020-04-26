package ar.com.kfgodel.diamond.impl.lambdas;

import ar.com.kfgodel.diamond.api.lambdas.Lambda;

/**
 * This type represents the definition of equality for lambdas
 * Created by kfgodel on 09/01/16.
 */
public class LambdaEquality {
  public static final LambdaEquality INSTANCE = new LambdaEquality();

  /**
   * Compares the given modifier with the object indicating if they are equals.<br>
   * They are equals if have same declaration
   *
   * @param one The modifier to compare
   * @param obj The object to be compared with
   * @return true if represent the same modifier
   */
  public boolean areEquals(Lambda one, Object obj) {
    if (one == obj) {
      return true;
    }
    if (one == null || obj == null || !(obj instanceof Lambda)) {
      return false;
    }
    Lambda other = (Lambda) obj;
    return one.asLambda().equals(other.asLambda());
  }

  /**
   * Generates the hashcode compatible with this equality definition
   *
   * @param typePackage The modifier to hash
   * @return The code that can be compared
   */
  public int hashcodeFor(Lambda typePackage) {
    return typePackage.asLambda().hashCode();
  }

}
