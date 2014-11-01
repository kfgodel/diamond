package ar.com.kfgodel.diamond.impl.members.modifiers.methods;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the abstract method modifier
 * Created by kfgodel on 19/10/14.
 */
public class AbstractModifier extends ModifierSupport {
    protected AbstractModifier() {
        super(Modifier.ABSTRACT, "abstract");
    }

    public static AbstractModifier create() {
        AbstractModifier modifier = new AbstractModifier();
        return modifier;
    }

}
