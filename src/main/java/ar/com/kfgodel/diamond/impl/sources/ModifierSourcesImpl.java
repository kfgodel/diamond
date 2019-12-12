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

  @Override
  public Nary<Modifier> from(Member nativeMember) {
    int modifiersBitmap = nativeMember.getModifiers();
    return fromMember(modifiersBitmap);
  }

  @Override
  public Nary<Modifier> fromMember(int modifierBitmap) {
    return Nary.from(calculateModifierList(modifierBitmap));
  }

  private List<Modifier> calculateModifierList(int modifierBitmap) {
    return cache.reuseOrCreateRepresentationFor(modifierBitmap, () -> {
      return createMemberModifierListFor(modifierBitmap);
    });
  }

  private List<Modifier> createMemberModifierListFor(int modifierBitmap) {
    return all()
      .filter((modifier) -> modifier.isPresentIn(modifierBitmap))
      .collect(Collectors.toList());
  }

  @Override
  public Nary<Modifier> all() {
    return Nary.from(Arrays.stream(Modifiers.values()));
  }

  @Override
  public Nary<Modifier> fromParameter(int modifierBitmap) {
    // We exclude package that is present by default (lack of other visibility)
    return fromMember(modifierBitmap)
      .filter((modifier) -> !Modifiers.PACKAGE.equals(modifier));
  }

  public static ModifierSourcesImpl create(DiamondCache cache) {
    ModifierSourcesImpl sources = new ModifierSourcesImpl();
    sources.cache = cache;
    return sources;
  }
}
