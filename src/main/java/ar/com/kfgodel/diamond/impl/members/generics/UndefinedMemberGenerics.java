package ar.com.kfgodel.diamond.impl.members.generics;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.members.TypeMember;

import java.util.function.Supplier;

/**
 * This type represents the undefined generics of a member type
 * Created by kfgodel on 01/11/14.
 */
public class UndefinedMemberGenerics implements Supplier<Generics> {

  private TypeMember member;

  @Override
  public Generics get() {
    throw new DiamondException("The generics information for member[" + member + "] was not defined");
  }

  public static UndefinedMemberGenerics create(TypeMember member) {
    UndefinedMemberGenerics generics = new UndefinedMemberGenerics();
    generics.member = member;
    return generics;
  }

}
