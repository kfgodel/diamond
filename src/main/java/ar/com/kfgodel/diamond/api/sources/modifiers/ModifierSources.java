package ar.com.kfgodel.diamond.api.sources.modifiers;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;

import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Stream;

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
    List<MemberModifier> from(Member nativeMember);

    /**
     * Obtains the modifier list from the int bitmap that the VM uses on native reflection
     * @param modifierBitmap The native modifier bitmap
     * @return The list of modifier member representation
     */
    List<MemberModifier> from(int modifierBitmap);

    /**
     * @return Gets the set of all available modifiers (field, methods, constructors, etc)
     */
    Stream<MemberModifier> all();
}
