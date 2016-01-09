package ar.com.kfgodel.diamond.impl.parameters;

import ar.com.kfgodel.diamond.api.equals.CompositeIdentityToken;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.impl.equals.ImmutableIdentityParts;

/**
 * This type represents the definition of equality for parameters
 * Created by kfgodel on 09/01/16.
 */
public class ParameterEquality {

  public static final ParameterEquality INSTANCE = new ParameterEquality();

  /**
   * Compares the given parameter with the object indicating if they are equals.<br>
   *     They are equals if have same name and type
   * @param one The method to compare
   * @param obj The object to be compared with
   * @return true if represent the same method
   */
  public boolean areEquals(ExecutableParameter one, Object obj){
    if(one == obj){
      return true;
    }
    if(one == null || obj == null || !(obj instanceof ExecutableParameter)){
      return false;
    }
    ExecutableParameter other = (ExecutableParameter) obj;
    return one.getIdentityToken().equals(other.getIdentityToken());
  }

  /**
   * Creates the identity token for the parameter represent its equality
   * @param parameter The parameter to represent
   * @return The composite token
   */
  public CompositeIdentityToken calculateTokenFor(ExecutableParameter parameter) {
    return ImmutableIdentityParts.create(
      parameter.name(),
      parameter.declaredType());
  }

}
