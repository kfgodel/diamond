package ar.com.kfgodel.diamond.impl.members.modifiers.methods;

import ar.com.kfgodel.diamond.impl.members.modifiers.ModifierSupport;

import java.lang.reflect.Modifier;

/**
 * This type represents the native method modifier
 * Created by kfgodel on 19/10/14.
 */
public class NativeModifier extends ModifierSupport {
    protected NativeModifier() {
        super(Modifier.NATIVE, "native");
    }

    public static NativeModifier create() {
        NativeModifier modifier = new NativeModifier();
        return modifier;
    }

}
