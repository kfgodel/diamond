package ar.com.kfgodel.diamond.impl.members.modifiers.suppliers;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.Member;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the immutable modifiers supplier for a member that doesn't change modifiers over time
 * Created by kfgodel on 18/10/14.
 */
public class ImmutableMemberModifiers {

    public static Supplier<Stream<MemberModifier>> create(Member nativeMember) {
        return StreamFromCollectionSupplier.using(SuppliedValue.lazilyBy(() -> Diamond.modifiers().from(nativeMember)));
    }

}
