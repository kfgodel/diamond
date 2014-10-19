package ar.com.kfgodel.diamond.impl.members.modifiers.visiblity;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the private visibility member modifier
 * Created by kfgodel on 18/10/14.
 */
public class ProtectedModifier extends ModifierSupport {
    protected ProtectedModifier() {
        super(Modifier.PROTECTED);
    }

    public static ProtectedModifier create() {
        ProtectedModifier modifier = new ProtectedModifier();
        return modifier;
    }

}
