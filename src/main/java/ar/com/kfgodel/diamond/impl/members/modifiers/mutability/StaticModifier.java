package ar.com.kfgodel.diamond.impl.members.modifiers.mutability;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the static member modifier
 * Created by kfgodel on 19/10/14.
 */
public class StaticModifier extends ModifierSupport{
    protected StaticModifier() {
        super(Modifier.STATIC);
    }

    public static StaticModifier create() {
        StaticModifier modifier = new StaticModifier();
        return modifier;
    }

}
