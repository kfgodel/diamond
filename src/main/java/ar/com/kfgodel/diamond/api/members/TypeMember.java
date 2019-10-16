package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.declaration.Declarable;
import ar.com.kfgodel.diamond.api.equals.TokenIdentifiable;
import ar.com.kfgodel.diamond.api.executables.Executable;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.generics.Generified;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiable;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a member of a type (field, method or constructor)
 * Created by kfgodel on 18/10/14.
 */
public interface TypeMember extends Named, Annotated, Modifiable, Generified, Declarable, Executable, DiamondReflection, TokenIdentifiable {

  /**
   * @return The type in which this member is declared and who owns this member
   */
  TypeInstance declaringType();

  /**
   * @return The generics information for this member
   */
  @Override
  Generics generics();

  /**
   * Indicates if this members belongs (an requires) an instance to be used on, or not.<br>
   * Static methods, static fields and constructors don't require an instance.
   *
   * @return true if this member requires an instance to be used
   */
  boolean isInstanceMember();
}
