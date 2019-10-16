package ar.com.kfgodel.diamond.impl.members.bound;

import ar.com.kfgodel.diamond.api.members.BoundMember;

import java.util.Objects;

/**
 * This type represents the equality definition for bound members (fields or methods)
 * Created by kfgodel on 06/01/16.
 */
public class BoundMemberEquality {

  public static final BoundMemberEquality INSTANCE = new BoundMemberEquality();


  /**
   * Compares bound members by equality using the bound instance and the bound type member
   *
   * @param one One member to compare
   * @param obj the other member
   * @return true if both represent the same type member and are bound to the same object
   */
  public boolean areEquals(BoundMember one, Object obj) {
    if (one == obj) {
      return true;
    }
    if (one == null || obj == null || !(obj instanceof BoundMember)) {
      return false;
    }
    BoundMember other = (BoundMember) obj;
    return one.instance() == other.instance() && one.typeMember().equals(other.typeMember());
  }

  /**
   * Generates a hashcode using the instance and type member as equality criteria
   *
   * @param boundMember The member to hash
   * @return The hashcode compatible with this equality definition
   */
  public int hash(BoundMember boundMember) {
    return Objects.hash(boundMember.instance(), boundMember.typeMember());
  }
}
