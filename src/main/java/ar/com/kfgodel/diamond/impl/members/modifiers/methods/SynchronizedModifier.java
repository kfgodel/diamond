package ar.com.kfgodel.diamond.impl.members.modifiers.methods;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the synchronized method modifier
 * Created by kfgodel on 19/10/14.
 */
public class SynchronizedModifier extends ModifierSupport {
    protected SynchronizedModifier() {
        super(Modifier.SYNCHRONIZED, "synchronized");
    }

    public static SynchronizedModifier create() {
        SynchronizedModifier modifier = new SynchronizedModifier();
        return modifier;
    }

}
