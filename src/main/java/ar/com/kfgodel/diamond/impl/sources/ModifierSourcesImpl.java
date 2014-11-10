package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.members.modifiers.*;
import ar.com.kfgodel.diamond.api.sources.ModifierSources;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

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

    private DiamondCache cache;

    public static ModifierSourcesImpl create(DiamondCache cache) {
        ModifierSourcesImpl sources = new ModifierSourcesImpl();
        sources.cache = cache;
        return sources;
    }

    @Override
    public List<Modifier> from(Member nativeMember) {
        int modifiersBitmap = nativeMember.getModifiers();
        return from(modifiersBitmap);
    }

    @Override
    public List<Modifier> from(int modifierBitmap) {
        return cache.reuseOrCreateRepresentationFor(modifierBitmap, ()-> createMemberModifierListFor(modifierBitmap));
    }

    private List<Modifier> createMemberModifierListFor(int modifierBitmap) {
        return all()
                .filter((modifier) -> modifier.isPresentIn(modifierBitmap))
                .collect(Collectors.toList());
    }

    @Override
    public List<Modifier> fromParameter(int modifierBitmap) {
        // We exclude package that is present by default (lack of other visibility)
        return from(modifierBitmap).stream()
                .filter((modifier) -> !Visibility.PACKAGE.equals(modifier))
                .collect(Collectors.toList());
    }

    @Override
    public Nary<Modifier> all() {
        return NaryFromNative.create(Stream.concat(
                Stream.concat(
                        Stream.concat(
                                Arrays.stream(Visibility.values()),
                                Arrays.stream(Mutability.values())),
                        Arrays.stream(FieldModifier.values())),
                Arrays.stream(MethodModifier.values())));
    }
}
