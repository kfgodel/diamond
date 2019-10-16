package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.api.sources.ModifierSources;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    return cache.reuseOrCreateRepresentationFor(modifierBitmap, () -> createMemberModifierListFor(modifierBitmap));
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
      .filter((modifier) -> !Modifiers.PACKAGE.equals(modifier))
      .collect(Collectors.toList());
  }

  @Override
  public Nary<Modifier> all() {
    return Nary.create(Arrays.stream(Modifiers.values()));
  }
}
