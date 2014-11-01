package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.ModifiableMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a member of a type (field, method or constructor)
 * Created by kfgodel on 18/10/14.
 */
public interface TypeMember extends ModifiableMember {

    /**
     * @return The type in which this member is declared and who owns this member
     */
    TypeInstance declaringType();

    /**
     * @return This member usable as a function with semantics depending on this instance, and the called signature
     */
    PolymorphicInvokable asFunction();

}
