package ar.com.kfgodel.diamond.impl.members.modifiers.visiblity;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the private visibility member modifier
 * Created by kfgodel on 18/10/14.
 */
public class PrivateModifier extends ModifierSupport {
  protected PrivateModifier() {
    super(Modifier.PRIVATE, "private");
  }

  public static PrivateModifier create() {
    PrivateModifier modifier = new PrivateModifier();
    return modifier;
  }

}
