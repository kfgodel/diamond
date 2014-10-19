package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents a member of a type (field, method or constructor)
 * Created by kfgodel on 18/10/14.
 */
public interface TypeMember {

    /**
     * @return The type in which this member is declared and who owns this member
     */
    TypeInstance declaringType();

    /**
     * @return The set of member modifiers applied to this member declaration
     */
    Stream<MemberModifier> modifiers();

}
