package ar.com.kfgodel.diamond.api.members.modifiers;

import ar.com.kfgodel.diamond.api.declaration.Declarable;

import java.util.function.Predicate;

/**
 * This type represents a modifier trait applied to a type member.<br>
 *     This modifier can modify member visibility, extensibility, floating point arithmetic, etc.
 *
 * Created by kfgodel on 18/10/14.
 */
public interface MemberModifier extends Predicate<MemberModifier>, Declarable {

    /**
     * Indicates if this modifier is coded as present in the given bitmap
     * @param modifierBitmap A modifier bitmap int
     * @return true if this modifier is 'active' or present in the bitmap. False if this modifier is not used
     */
    boolean isPresentIn(int modifierBitmap);

    /**
     * @return The declaration string for this modifier (as present in sources)
     */
    String declaration();
}
