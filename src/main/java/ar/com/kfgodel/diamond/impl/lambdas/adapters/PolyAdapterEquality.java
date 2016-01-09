package ar.com.kfgodel.diamond.impl.lambdas.adapters;

/**
 * This type defines the equality criteria for poly adapters
 * Created by kfgodel on 09/01/16.
 */
public class PolyAdapterEquality {

  public static final PolyAdapterEquality INSTANCE = new PolyAdapterEquality();

  /**
   * Compares the given adapter with the object indicating if they are equals.<br>
   *     They are equals if have same declaration
   * @param one The modifier to compare
   * @param obj The object to be compared with
   * @return true if represent the same code
   */
  public boolean areEquals(PolyAdapter one, Object obj){
    if(one == obj){
      return true;
    }
    if(one == null || obj == null || !(obj instanceof PolyAdapter)){
      return false;
    }
    PolyAdapter other = (PolyAdapter) obj;
    return one.adaptedCode().equals(other.adaptedCode());
  }

  /**
   * Generates the hashcode compatible with this equality definition
   * @param adapter The modifier to hash
   * @return The code that can be compared
   */
  public int hashcodeFor(PolyAdapter adapter){
    return adapter.adaptedCode().hashCode();
  }

}
