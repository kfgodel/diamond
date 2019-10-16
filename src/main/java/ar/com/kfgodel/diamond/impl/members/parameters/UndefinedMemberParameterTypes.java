package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents an undefined member parameters
 * Created by kfgodel on 01/11/14.
 */
public class UndefinedMemberParameterTypes implements Supplier<Nary<TypeInstance>> {

  private TypeMember member;

  @Override
  public Nary<TypeInstance> get() {
    throw new DiamondException("The parameters for the member[" + member + "] were not defined");
  }

  public static UndefinedMemberParameterTypes create(TypeMember member) {
    UndefinedMemberParameterTypes parameters = new UndefinedMemberParameterTypes();
    parameters.member = member;
    return parameters;
  }

}
