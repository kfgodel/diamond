package ar.com.kfgodel.diamond.impl.equals;

import ar.com.kfgodel.diamond.api.equals.CompositeIdentityToken;

import java.util.Collection;
import java.util.Objects;

/**
 * This type represents a composed identity token that never changes its parts.<br>
 * Because the parts are never changed it's possible to cache hascodes
 * <p>
 * Created by kfgodel on 24/11/14.
 */
public class ImmutableIdentityParts implements CompositeIdentityToken {

  public static final ImmutableIdentityParts NO_PARTS = ImmutableIdentityParts.create(new Object[0]);

  private Object[] parts;
  private int[] partHashcodes;
  private int hashcode;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof CompositeIdentityToken)) {
      return false;
    }
    CompositeIdentityToken other = (CompositeIdentityToken) obj;
    return isEqualTo(other);
  }

  /**
   * Compares this instance to the given composite identity
   *
   * @param other The composite identity to compare
   * @return true if this is considerable equal
   */
  private boolean isEqualTo(CompositeIdentityToken other) {
    if (other instanceof ImmutableIdentityParts) {
      // If the other is immutable we can compare hashcodes first
      ImmutableIdentityParts immutableOther = (ImmutableIdentityParts) other;
      return this.hashCode() == immutableOther.hashCode() && hasEqualHashPartsTo(immutableOther);
    }
    return hasEqualPartsTo(other);
  }

  /**
   * Compare parts by hashcodes first
   *
   * @param other The other with hashcoded parts
   * @return False if any hashcode differ, true it this instance is equal to the other
   */
  private boolean hasEqualHashPartsTo(ImmutableIdentityParts other) {
    int[] otherPartsHashCode = other.getPartHashcodes();
    if (this.getPartHashcodes().length != otherPartsHashCode.length) {
      return false;
    }
    for (int i = 0; i < getPartHashcodes().length; i++) {
      if (getPartHashcodes()[i] != otherPartsHashCode[i]) {
        return false;
      }
    }
    return hasEqualPartsTo(other);
  }

  /**
   * Compares the parts of this instances with the ones on the other
   *
   * @param other The instance to compare
   * @return True if this instance has equal parts
   */
  private boolean hasEqualPartsTo(CompositeIdentityToken other) {
    Object[] otherParts = other.getParts();
    if (this.parts.length != otherParts.length) {
      return false;
    }
    for (int i = 0; i < parts.length; i++) {
      Object thisPart = parts[i];
      Object otherPart = otherParts[i];
      if (!Objects.equals(thisPart, otherPart)) {
        return false;
      }
    }
    return true;
  }


  @Override
  public int hashCode() {
    if (partHashcodes == null) {
      // hash not calculated yet
      calculateHashes();
    }
    return this.hashcode;
  }

  /**
   * Calculates the hascode for this instance, as the parts as well
   */
  private void calculateHashes() {
    this.hashcode = 1;
    this.partHashcodes = new int[parts.length];
    for (int i = 0; i < parts.length; i++) {
      Object part = parts[i];
      partHashcodes[i] = (part == null ? 0 : part.hashCode());
      hashcode = 31 * hashcode + partHashcodes[i];
    }
  }

  @Override
  public Object[] getParts() {
    return parts;
  }

  public int[] getPartHashcodes() {
    if (partHashcodes == null) {
      calculateHashes();
    }
    return partHashcodes;
  }

  public static ImmutableIdentityParts create(Object... parts) {
    if (parts.length == 0 && NO_PARTS != null) {
      // Reuse the instance to speed comparisons
      return NO_PARTS;
    }
    ImmutableIdentityParts token = new ImmutableIdentityParts();
    token.parts = parts;
    return token;
  }

  public static ImmutableIdentityParts create(Collection<?> parts) {
    if (parts.isEmpty() && NO_PARTS != null) {
      // Reuse instance to speed comparisons
      return NO_PARTS;
    }
    return create(parts.toArray());
  }


}
