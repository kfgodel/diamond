package ar.com.kfgodel.diamond.impl.members.modifiers.visiblity;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the public visibility member modifier
 * Created by kfgodel on 18/10/14.
 */
public class PublicModifier extends ModifierSupport {
    protected PublicModifier() {
        super(Modifier.PUBLIC, "public");
    }

    public static PublicModifier create() {
        PublicModifier modifier = new PublicModifier();
        return modifier;
    }

}
