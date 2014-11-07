package ar.com.kfgodel.diamond.api.members.modifiers;

import java.util.stream.Stream;

/**
 * This type represents a type member that can be modified with member modifiers
 * Created by kfgodel on 01/11/14.
 */
public interface Modifiable {

    /**
     * @return The set of member modifiers applied to this member declaration
     */
    Stream<Modifier> modifiers();

    /**
     * Verifies if the given modifier is present in this instance
     * @param expectedModifier The modifier to verify
     * @return True if this instance is modified with the given modifier
     */
    boolean is(Modifier expectedModifier);

}
