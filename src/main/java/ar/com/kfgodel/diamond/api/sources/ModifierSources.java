package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Member;
import java.util.List;

/**
 * This type represents the possible sources for member modifiers
 * Created by kfgodel on 18/10/14.
 */
public interface ModifierSources {
    /**
     * Obtains the modifier representations for the native member instance
     * @param nativeMember The native type member
     * @return The list of modifiers found on the native member
     */
    List<Modifier> from(Member nativeMember);

    /**
     * Obtains the modifier list from the int bitmap that the VM uses on native reflection types.<br>
     *     If no visibility bit present, then "default" visibility is assumed (for type members).<br>
     * This method is not fit for parameter modifiers that follow a different logic. Use fromParameter(int) instead
     *
     * @param modifierBitmap The native modifier bitmap
     * @return The list of modifier member representation
     */
    List<Modifier> from(int modifierBitmap);

    /**
     * Obtains the modifier list from the parameter int bitmap (only final is currently in use)
     * @param modifierBitmap The parameter modifier bitmap
     * @return The list of parameter modifiers
     */
    List<Modifier> fromParameter(int modifierBitmap);


    /**
     * @return Gets the set of all available modifiers (field, methods, constructors, etc)
     */
    Nary<Modifier> all();
}
