package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.members.modifiers.*;
import ar.com.kfgodel.diamond.api.sources.modifiers.ModifierSources;

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
    public List<Modifier> from(Member nativeMember) {
        int modifiersBitmap = nativeMember.getModifiers();
        return from(modifiersBitmap);
    }

    @Override
    public List<Modifier> from(int modifierBitmap) {
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
    public Stream<Modifier> all() {
        return Stream.concat(
                Stream.concat(
                Stream.concat(
                Arrays.stream(Visibility.values()),
                Arrays.stream(Mutability.values())),
                Arrays.stream(FieldModifier.values())),
                Arrays.stream(MethodModifier.values()));
    }
}
