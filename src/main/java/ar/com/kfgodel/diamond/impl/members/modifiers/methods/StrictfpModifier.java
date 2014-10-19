package ar.com.kfgodel.diamond.impl.members.modifiers.methods;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the strictfp method modifier
 * Created by kfgodel on 19/10/14.
 */
public class StrictfpModifier extends ModifierSupport {
    protected StrictfpModifier() {
        super(Modifier.STRICT);
    }

    public static StrictfpModifier create() {
        StrictfpModifier modifier = new StrictfpModifier();
        return modifier;
    }

}
