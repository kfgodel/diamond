package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.members.modifiers.*;
import ar.com.kfgodel.diamond.api.sources.modifiers.*;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type implements the sources for type member modifiers
 * Created by kfgodel on 18/10/14.
 */
public class ModifierSourcesImpl implements ModifierSources {

    public static ModifierSourcesImpl create() {
        ModifierSourcesImpl sources = new ModifierSourcesImpl();
        return sources;
    }

    @Override
    public List<MemberModifier> from(Member nativeMember) {
        int modifiersBitmap = nativeMember.getModifiers();
        return from(modifiersBitmap);
    }

    @Override
    public List<MemberModifier> from(int modifierBitmap) {
        return all()
                .filter((modifier) -> modifier.isPresentIn(modifierBitmap))
                .collect(Collectors.toList());
    }

    @Override
    public Stream<MemberModifier> all() {
        return Stream.concat(
                Stream.concat(
                Stream.concat(
                Arrays.stream(Visibility.values()),
                Arrays.stream(Mutability.values())),
                Arrays.stream(FieldModifier.values())),
                Arrays.stream(MethodModifier.values()));
    }
}
