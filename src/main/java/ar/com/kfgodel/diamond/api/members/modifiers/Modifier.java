package ar.com.kfgodel.diamond.api.members.modifiers;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.declaration.Declarable;

import java.util.function.Predicate;

/**
 * This type represents a modifier trait applied to a type member.<br>
 *     This modifier can modify member visibility, extensibility, floating point arithmetic, etc.
 *
 * Created by kfgodel on 18/10/14.
 */
public interface Modifier extends Predicate<Modifier>, Declarable, DiamondReflection {

    /**
     * Indicates if this modifier is coded as present in the given bitmap
     *
     * @param modifierBitmap A modifier bitmap int
     * @return true if this modifier is 'active' or present in the bitmap. False if this modifier is not used
     */
    boolean isPresentIn(int modifierBitmap);

    /**
     * @return The declaration string for this modifier (as present in sources)
     */
    String declaration();


    /**
     * @return Overrided hashcode to match equals definition
     */
    @Override
    public int hashCode();

    /**
     * Compares this instance to another identifier by declaration
     * @param obj The object to compare
     * @return true if obj represents an identifier with same declaration
     */
    @Override
    public boolean equals(Object obj);
}
