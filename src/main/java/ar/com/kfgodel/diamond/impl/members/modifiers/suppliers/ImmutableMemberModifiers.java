package ar.com.kfgodel.diamond.impl.members.modifiers.suppliers;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Member;
import java.util.function.Supplier;

/**
 * This type represents the immutable modifiers supplier for a member that doesn't change modifiers over time
 * Created by kfgodel on 18/10/14.
 */
public class ImmutableMemberModifiers {

  public static Supplier<Nary<Modifier>> create(Member nativeMember) {
    return CachedValues.from(() -> {
      return Diamond.modifiers().from(nativeMember);
    });
  }

}
