package ar.com.kfgodel.diamond.api.sources.modifiers;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.fields.TransientModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.fields.VolatileModifier;

/**
 * This type represents the other modifiers applicable to fields
 * Created by kfgodel on 19/10/14.
 */
public class FieldModifier {

    /**
     * The value of this member shouldn't be serialized when serializing an instance
     * of the declaring type
     */
    public static final TransientModifier TRANSIENT = TransientModifier.create();

    /**
     * The update operations on this member should be synchronized in memory to preserve
     * other threads interaction semantics
     */
    public static final VolatileModifier VOLATILE = VolatileModifier.create();


    private static final MemberModifier[] values = new MemberModifier[]{TRANSIENT, VOLATILE};

    public static MemberModifier[] values(){
        return values;
    }
}
