package ar.com.kfgodel.diamond.impl.members.modifiers.mutability;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the final member modifier
 * Created by kfgodel on 19/10/14.
 */
public class FinalModifier extends ModifierSupport {
  protected FinalModifier() {
    super(Modifier.FINAL, "final");
  }

  public static FinalModifier create() {
    FinalModifier modifier = new FinalModifier();
    return modifier;
  }

}
