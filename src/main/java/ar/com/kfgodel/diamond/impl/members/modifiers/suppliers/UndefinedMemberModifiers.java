package ar.com.kfgodel.diamond.impl.members.modifiers.suppliers;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the undefined modifiers for a member that generates an error
 * Created by kfgodel on 18/10/14.
 */
public class UndefinedMemberModifiers implements Supplier<Nary<MemberModifier>> {

    private TypeMember member;

    @Override
    public Nary<MemberModifier> get() {
        throw new DiamondException("Modifiers weren't defined for this member: " + member);
    }

    public static UndefinedMemberModifiers create(TypeMember member) {
        UndefinedMemberModifiers supplier = new UndefinedMemberModifiers();
        supplier.member = member;
        return supplier;
    }

}
