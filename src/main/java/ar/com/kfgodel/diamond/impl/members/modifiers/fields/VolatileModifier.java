package ar.com.kfgodel.diamond.impl.members.modifiers.fields;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the volatile field modifier
 * Created by kfgodel on 19/10/14.
 */
public class VolatileModifier extends ModifierSupport {
  protected VolatileModifier() {
    super(Modifier.VOLATILE, "volatile");
  }

  public static VolatileModifier create() {
    VolatileModifier modifier = new VolatileModifier();
    return modifier;
  }

}
