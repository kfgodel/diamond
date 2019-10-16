package ar.com.kfgodel.diamond.impl.invokables;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.TypeMember;

import java.util.function.Supplier;

/**
 * This type represents an undefined value for a type member
 * Created by kfgodel on 30/10/14.
 */
public class UndefinedInvoker implements Supplier<PolymorphicInvokable> {

  private TypeMember member;

  @Override
  public PolymorphicInvokable get() {
    throw new DiamondException("This member[" + member + "] doesn't define an invoker");
  }

  public static UndefinedInvoker create(TypeMember member) {
    UndefinedInvoker invoker = new UndefinedInvoker();
    invoker.member = member;
    return invoker;
  }

}
