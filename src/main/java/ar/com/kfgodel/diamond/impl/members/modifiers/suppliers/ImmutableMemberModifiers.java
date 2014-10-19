package ar.com.kfgodel.diamond.impl.members.modifiers.suppliers;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.lang.reflect.Member;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the immutable modifiers supplier for a member that doesn't change modifiers over time
 * Created by kfgodel on 18/10/14.
 */
public class ImmutableMemberModifiers implements Supplier<Stream<MemberModifier>> {

    /**
     * We cache the list as modifiers don't change
     */
    private LazyValue<List<MemberModifier>> modifiersList;

    @Override
    public Stream<MemberModifier> get() {
        return modifiersList.get().stream();
    }

    public static ImmutableMemberModifiers create(Member nativeMember) {
        ImmutableMemberModifiers modifiers = new ImmutableMemberModifiers();
        modifiers.modifiersList = SuppliedValue.from(() -> Diamond.modifiers().from(nativeMember));
        return modifiers;
    }

}
