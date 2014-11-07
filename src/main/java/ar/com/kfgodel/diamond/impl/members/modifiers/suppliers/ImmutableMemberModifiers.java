package ar.com.kfgodel.diamond.impl.members.modifiers.suppliers;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.reflect.Member;
import java.util.function.Supplier;

/**
 * This type represents the immutable modifiers supplier for a member that doesn't change modifiers over time
 * Created by kfgodel on 18/10/14.
 */
public class ImmutableMemberModifiers {

    public static Supplier<Nary<MemberModifier>> create(Member nativeMember) {
        return NaryFromCollectionSupplier.lazilyBy(() -> Diamond.modifiers().from(nativeMember));
    }

}
