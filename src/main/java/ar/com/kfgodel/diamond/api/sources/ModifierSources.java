package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Member;

/**
 * This type represents the possible sources for member modifiers
 * Created by kfgodel on 18/10/14.
 */
public interface ModifierSources {
  /**
   * @return Gets the set of all available native modifiers (field, methods, constructors, etc)
   */
  Nary<Modifier> all();

  /**
   * Obtains the modifiers that the given native member has.<br>
   * Modifiers are natively represented as a bitmap, this method allows getting object representation for each
   * of the available modifiers on the member
   *
   * @param nativeMember The native type member
   * @return The modifiers found on the native member
   */
  Nary<Modifier> from(Member nativeMember);

  /**
   * Obtains the modifiers from the class member int bitmap that the VM uses on native reflection objects
   * (methods, fields, constructors).<br>
   * If no visibility bit present, then "default" visibility is assumed.<br>
   * This method is not fit for parameter modifier bitmaps that follow a different logic.
   * Use {@link #fromParameter(int)} instead
   *
   * @param modifierBitmap The native member modifier bitmap
   * @return The list of modifier member representation
   */
  Nary<Modifier> fromMember(int modifierBitmap);

  /**
   * Obtains the modifiers from the parameter int bitmap (only final is currently in use).<br>
   *   This methods is for parameter modifiers only, not class member bitmaps
   *
   * @param modifierBitmap The parameter modifier bitmap
   * @return The list of parameter modifiers
   */
  Nary<Modifier> fromParameter(int modifierBitmap);
}
