package ar.com.kfgodel.diamond.impl.types.generics.parameters.substitutions;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.util.List;

/**
 * This type represents a list element substitution
 * Created by kfgodel on 27/09/14.
 */
public class ValueSubstitution {

  private int replacedIndex;
  private int replacementIndex;

  public static ValueSubstitution create(int substitutedIndex, int substituteIndex) {
    ValueSubstitution substitution = new ValueSubstitution();
    substitution.replacedIndex = substitutedIndex;
    substitution.replacementIndex = substituteIndex;
    return substitution;
  }

  public <T> void executeOn(List<T> replacementValues, List<T> replacedValues) {
    if (replacementIndex >= replacementValues.size()) {
      throw new DiamondException("The replacement index[" + replacementIndex + "] exceeds replacement values size" + replacementValues);
    }
    if (replacedIndex >= replacedValues.size()) {
      throw new DiamondException("The replaced index[" + replacedIndex + "] exceeds replaced values size" + replacedValues);
    }
    T replacement = replacementValues.get(replacementIndex);
    replacedValues.set(replacedIndex, replacement);
  }

}
