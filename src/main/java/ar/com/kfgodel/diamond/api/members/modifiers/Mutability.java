package ar.com.kfgodel.diamond.api.members.modifiers;

import ar.com.kfgodel.diamond.impl.members.modifiers.mutability.FinalModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.mutability.StaticModifier;

/**
 * This type represents the mutability modifiers usable with methods and field members.<br>
 *     It emulates a enum like accessors
 * Created by kfgodel on 19/10/14.
 */
public class Mutability {
    /**
     * The value of this member cannot be changed once defined.<br>
     *     For fields it cannot be assigned twice.
     *     For methods it cannot be re-defined by subclasses.
     */
    public static final FinalModifier FINAL = FinalModifier.create();

    /**
     * The value of this member is allocated in class space, instead of instance space (one value per class).
     * It cannot be re-defined by subclasses but its value can be changed on a class level
     */
    public static final StaticModifier STATIC = StaticModifier.create();


    private static final Modifier[] values = new Modifier[]{FINAL, STATIC};

    public static Modifier[] values(){
        return values;
    }


}
