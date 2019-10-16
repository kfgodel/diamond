package ar.com.kfgodel.diamond.api.members.modifiers;

import ar.com.kfgodel.diamond.impl.members.modifiers.fields.TransientModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.fields.VolatileModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.methods.AbstractModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.methods.NativeModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.methods.StrictfpModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.methods.SynchronizedModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.mutability.FinalModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.mutability.StaticModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.PackageModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.PrivateModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.ProtectedModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.PublicModifier;

/**
 * This type serves as a single access point to the standard java modifiers
 * *
 * Created by kfgodel on 03/02/15.
 */
public class Modifiers {

  /**
   * The value of this member cannot be changed once defined.<br>
   * For fields it cannot be assigned twice.
   * For methods it cannot be re-defined by subclasses.
   */
  public static final FinalModifier FINAL = FinalModifier.create();

  /**
   * The value of this member is allocated in class space, instead of instance space (one value per class).
   * It cannot be re-defined by subclasses but its value can be changed on a class level
   */
  public static final StaticModifier STATIC = StaticModifier.create();


  /**
   * Accessible to other types in other packages<br>
   * Inaccessible to none.
   */
  public static final PublicModifier PUBLIC = PublicModifier.create();
  /**
   * Accessible to types in same package, and child types in other packages.<br>
   * Inaccessible to non child types in other packages
   */
  public static final ProtectedModifier PROTECTED = ProtectedModifier.create();
  /**
   * Accessible to types in same package.<br>
   * Inaccessible to types in other packages (even child types)
   */
  public static final PackageModifier PACKAGE = PackageModifier.create();
  /**
   * Accessible to none.<br>
   * Inaccessible to any other type
   */
  public static final Modifier PRIVATE = PrivateModifier.create();


  /**
   * The value of this member shouldn't be serialized when serializing an instance
   * of the declaring type
   */
  public static final AbstractModifier ABSTRACT = AbstractModifier.create();

  /**
   * The update operations on this member should be synchronized in memory to preserve
   * other threads interaction semantics
   */
  public static final SynchronizedModifier SYNCHRONIZED = SynchronizedModifier.create();

  /**
   * The update operations on this member should be synchronized in memory to preserve
   * other threads interaction semantics
   */
  public static final NativeModifier NATIVE = NativeModifier.create();

  /**
   * The update operations on this member should be synchronized in memory to preserve
   * other threads interaction semantics
   */
  public static final StrictfpModifier STRICTFP = StrictfpModifier.create();


  /**
   * The value of this member shouldn't be serialized when serializing an instance
   * of the declaring type
   */
  public static final TransientModifier TRANSIENT = TransientModifier.create();

  /**
   * The update operations on this member should be synchronized in memory to preserve
   * other threads interaction semantics
   */
  public static final VolatileModifier VOLATILE = VolatileModifier.create();


  private static final Modifier[] visibility = new Modifier[]{PUBLIC, PRIVATE, PROTECTED, PACKAGE};

  public static Modifier[] visibilityValues() {
    return visibility;
  }


  private static final Modifier[] mutability = new Modifier[]{FINAL, STATIC};

  public static Modifier[] mutabilityValues() {
    return mutability;
  }


  private static final Modifier[] methodModifiers = new Modifier[]{ABSTRACT, SYNCHRONIZED, NATIVE, STRICTFP};

  public static Modifier[] methodModifierValues() {
    return methodModifiers;
  }


  private static final Modifier[] fieldModifiers = new Modifier[]{TRANSIENT, VOLATILE};

  public static Modifier[] fieldModifiersValues() {
    return fieldModifiers;
  }


  private static final Modifier[] values = new Modifier[]{PUBLIC, PRIVATE, PROTECTED, PACKAGE, FINAL, STATIC, ABSTRACT, SYNCHRONIZED, NATIVE, STRICTFP, TRANSIENT, VOLATILE};

  public static Modifier[] values() {
    return values;
  }


}
