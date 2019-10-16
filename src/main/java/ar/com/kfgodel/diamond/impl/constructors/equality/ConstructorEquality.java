package ar.com.kfgodel.diamond.impl.constructors.equality;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.equals.CompositeIdentityToken;
import ar.com.kfgodel.diamond.impl.equals.ImmutableIdentityParts;

import java.util.stream.Collectors;

/**
 * This type captures the concept of equality for TypeConstructor
 * Created by kfgodel on 16/10/14.
 */
public class ConstructorEquality {

  public static final ConstructorEquality INSTANCE = new ConstructorEquality();

  /**
   * Compares as constructor with other object to define equality.<br>
   * Two constructors are equals if declared in same type and have same parameters
   *
   * @param one The constructor to compare
   * @param obj The object to be compared with
   * @return true if they represent the same constructor
   */
  public boolean areEquals(TypeConstructor one, Object obj) {
    if (one == obj) {
      return true;
    }
    if (one == null || obj == null || !(obj instanceof TypeConstructor)) {
      return false;
    }
    TypeConstructor other = (TypeConstructor) obj;
    return one.getIdentityToken().equals(other.getIdentityToken());
  }

  /**
   * Creates the constructor token identifier
   *
   * @param constructor The constructor to represent
   * @return The composite token
   */
  public CompositeIdentityToken calculateTokenFor(TypeConstructor constructor) {
    return ImmutableIdentityParts.create(
      constructor.declaringType(),
      ImmutableIdentityParts.create(constructor.parameterTypes().collect(Collectors.toList())));
  }

}
