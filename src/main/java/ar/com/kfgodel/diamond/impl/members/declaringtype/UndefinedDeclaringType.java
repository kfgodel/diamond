package ar.com.kfgodel.diamond.impl.members.declaringtype;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;

/**
 * This type represents the supplier for an undefined declaring type
 * Created by kfgodel on 01/11/14.
 */
public class UndefinedDeclaringType implements Supplier<TypeInstance> {

  private TypeMember member;

  @Override
  public TypeInstance get() {
    throw new DiamondException("A declaring type definition was not provided from a subclass for this member: " + this.member);
  }

  public static UndefinedDeclaringType create(TypeMember member) {
    UndefinedDeclaringType declaringType = new UndefinedDeclaringType();
    declaringType.member = member;
    return declaringType;
  }

}
