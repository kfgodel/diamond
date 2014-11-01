package ar.com.kfgodel.diamond.impl.members.modifiers.fields;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the transient field modifier
 * Created by kfgodel on 19/10/14.
 */
public class TransientModifier extends ModifierSupport {
    protected TransientModifier() {
        super(Modifier.TRANSIENT, "transient");
    }

    public static TransientModifier create() {
        TransientModifier modifier = new TransientModifier();
        return modifier;
    }

}
