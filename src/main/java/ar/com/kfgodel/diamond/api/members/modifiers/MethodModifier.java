package ar.com.kfgodel.diamond.api.members.modifiers;

import ar.com.kfgodel.diamond.impl.members.modifiers.methods.AbstractModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.methods.NativeModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.methods.StrictfpModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.methods.SynchronizedModifier;

/**
 * This type represents the other modifiers applicable to methos
 * Created by kfgodel on 19/10/14.
 */
public class MethodModifier {

    /**
     * The value of this member shouldn't be serialized when serializing an instance
     * of the declaring type
     */
    public static final AbstractModifier ABSTRACT = AbstractModifier.create();

    /**
     * The update operations on this member should be synchronized in memory to preserve
     * other threads interaction semantics
     */
    public static final SynchronizedModifier SYNCHRONIZED = SynchronizedModifier.create();

    /**
     * The update operations on this member should be synchronized in memory to preserve
     * other threads interaction semantics
     */
    public static final NativeModifier NATIVE = NativeModifier.create();

    /**
     * The update operations on this member should be synchronized in memory to preserve
     * other threads interaction semantics
     */
    public static final StrictfpModifier STRICTFP = StrictfpModifier.create();


    private static final Modifier[] values = new Modifier[]{ABSTRACT, SYNCHRONIZED, NATIVE, STRICTFP};

    public static Modifier[] values(){
        return values;
    }
}
