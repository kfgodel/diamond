package ar.com.kfgodel.diamond.impl.members.modifiers;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;

/**
 * This type represents the equality definition for modifiers
 * Created by kfgodel on 09/01/16.
 */
public class ModifierEquality {
  public static final ModifierEquality INSTANCE = new ModifierEquality();

  /**
   * Compares the given modifier with the object indicating if they are equals.<br>
   * They are equals if have same declaration
   *
   * @param one The modifier to compare
   * @param obj The object to be compared with
   * @return true if represent the same modifier
   */
  public boolean areEquals(Modifier one, Object obj) {
    if (one == obj) {
      return true;
    }
    if (one == null || obj == null || !(obj instanceof Modifier)) {
      return false;
    }
    Modifier other = (Modifier) obj;
    return one.declaration().equals(other.declaration());
  }

  /**
   * Generates the hashcode compatible with this equality definition
   *
   * @param typePackage The modifier to hash
   * @return The code that can be compared
   */
  public int hashcodeFor(Modifier typePackage) {
    return typePackage.declaration().hashCode();
  }
}
